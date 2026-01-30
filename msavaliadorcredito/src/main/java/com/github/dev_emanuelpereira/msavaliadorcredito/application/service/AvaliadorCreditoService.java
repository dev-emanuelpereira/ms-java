package com.github.dev_emanuelpereira.msavaliadorcredito.application.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.dev_emanuelpereira.msavaliadorcredito.application.exception.DadosClienteNotFoundException;
import com.github.dev_emanuelpereira.msavaliadorcredito.application.exception.ErroComunicacaoMicroservicesException;
import com.github.dev_emanuelpereira.msavaliadorcredito.application.exception.ErroSolicitacaoCartaoException;
import com.github.dev_emanuelpereira.msavaliadorcredito.domain.model.*;
import com.github.dev_emanuelpereira.msavaliadorcredito.infra.clients.CartoesResourceClient;
import com.github.dev_emanuelpereira.msavaliadorcredito.infra.clients.ClienteResourceClient;
import com.github.dev_emanuelpereira.msavaliadorcredito.infra.mqueue.SolicitacaoEmissaoCartaoPublisher;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AvaliadorCreditoService {

    private final ClienteResourceClient clientesClient;
    private final CartoesResourceClient cartoesClient;
    private final SolicitacaoEmissaoCartaoPublisher solicitacaoEmissaoCartaoPublisher;

    public SituacaoCliente obterSituacaoCliente(String cpf) throws DadosClienteNotFoundException, ErroComunicacaoMicroservicesException {
        try {
        ResponseEntity<DadosCliente> dadosClienteResponse = clientesClient.dadosClientes(cpf) ;
        ResponseEntity<List<CartaoCliente>> dadosCartaoClienteResponse = cartoesClient.getCartoesByCliente(cpf) ;


        return SituacaoCliente.builder()
                .cliente(dadosClienteResponse.getBody())
                .cartoes(dadosCartaoClienteResponse.getBody())
                .build();
        } catch (FeignException.FeignClientException e) {
            int status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new DadosClienteNotFoundException();
            }
            throw new ErroComunicacaoMicroservicesException(e.getMessage(), status);
        }
    }

    public RetornoAvaliacaoCliente realizarAvaliacao(String cpf, Long renda) throws DadosClienteNotFoundException, ErroComunicacaoMicroservicesException {
        try {
                ResponseEntity<DadosCliente> dadosClienteResponse = clientesClient.dadosClientes(cpf);
                ResponseEntity<List<Cartao>> cartoesResponse = cartoesClient.getCartaoRendaAte(renda);

                List<Cartao> cartoes = cartoesResponse.getBody();
                var listaCartoesAprovados = cartoes.stream().map(cartao -> {

                            DadosCliente dadosCliente = dadosClienteResponse.getBody();

                            BigDecimal limiteBasico = cartao.getLimiteBasico();
                            BigDecimal rendaBD = BigDecimal.valueOf(renda);
                            BigDecimal idadeBD = BigDecimal.valueOf(dadosCliente.getIdade());

                            var fator = idadeBD.divide(BigDecimal.valueOf(10));
                            BigDecimal limiteAprovado = fator.multiply(limiteBasico);

                            CartaoAprovado aprovado = new CartaoAprovado();
                            aprovado.setCartao(cartao.getNome());
                            aprovado.setBandeira(cartao.getBandeira());
                            aprovado.setLimiteAprovado(cartao.getLimiteBasico());

                            return aprovado;
                        }
                ).toList();

                return new RetornoAvaliacaoCliente(listaCartoesAprovados);
            } catch (FeignException.FeignClientException e) {
                int status = e.status();
                if (HttpStatus.NOT_FOUND.value() == status) {
                    throw new DadosClienteNotFoundException();
                }
                throw new ErroComunicacaoMicroservicesException(e.getMessage(), status);

            }
        }

    public ProtocoloSolicitacaoCartao solicitacaoEmissaoCartao(DadosSolicitacaoEmissaoCartao dados) {
        try {
            solicitacaoEmissaoCartaoPublisher.solicitarCartao(dados);
            var protocolo = UUID.randomUUID().toString();

            return new ProtocoloSolicitacaoCartao(protocolo);
        } catch (Exception e) {
            throw new ErroSolicitacaoCartaoException(e.getMessage());
        }
    }
}


