package com.github.dev_emanuelpereira.msavaliadorcredito.application.service;

import com.github.dev_emanuelpereira.msavaliadorcredito.domain.model.CartaoCliente;
import com.github.dev_emanuelpereira.msavaliadorcredito.domain.model.DadosCliente;
import com.github.dev_emanuelpereira.msavaliadorcredito.domain.model.SituacaoCliente;
import com.github.dev_emanuelpereira.msavaliadorcredito.infra.clients.CartoesResourceClient;
import com.github.dev_emanuelpereira.msavaliadorcredito.infra.clients.ClienteResourceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AvaliadorCreditoService {

    private final ClienteResourceClient clientesClient;
    private final CartoesResourceClient cartoesClient;

    public SituacaoCliente obterSituacaoCliente(String cpf) {

        ResponseEntity<DadosCliente> dadosClienteResponse = clientesClient.dadosClientes(cpf) ;
        ResponseEntity<List<CartaoCliente>> dadosCartaoClienteResponse = cartoesClient.getCartoesByCliente(cpf) ;


        return SituacaoCliente.builder()
                .cliente(dadosClienteResponse.getBody())
                .cartoes(dadosCartaoClienteResponse.getBody())
                .build();
    }
}
