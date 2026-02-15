package com.github.dev_emanuelpereira.msavaliadorcredito.application;

import com.github.dev_emanuelpereira.msavaliadorcredito.application.exception.DadosClienteNotFoundException;
import com.github.dev_emanuelpereira.msavaliadorcredito.application.exception.ErroComunicacaoMicroservicesException;
import com.github.dev_emanuelpereira.msavaliadorcredito.application.exception.ErroSolicitacaoCartaoException;
import com.github.dev_emanuelpereira.msavaliadorcredito.application.service.AvaliadorCreditoService;
import com.github.dev_emanuelpereira.msavaliadorcredito.domain.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("avaliacoes-credito")
@RequiredArgsConstructor
public class AvaliadorCreditoController {

    private final AvaliadorCreditoService avaliadorCreditoService;

    @GetMapping(value = "situacao-cliente", params = "cpf")
    public ResponseEntity consultaSituacaoCliente(@RequestParam("cpf") String cpf) {
        SituacaoCliente situacaoCliente = null;
        try {
            situacaoCliente = avaliadorCreditoService.obterSituacaoCliente(cpf);
        } catch (DadosClienteNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ErroComunicacaoMicroservicesException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
        return ResponseEntity.ok(situacaoCliente);
    }

    @PostMapping
    public ResponseEntity realizarAvaliacao(@RequestBody DadosAvaliacao dados) {
        try {
            RetornoAvaliacaoCliente retornoAvaliacaoCliente = avaliadorCreditoService.realizarAvaliacao(dados.getCpf(), dados.getRenda());
            return ResponseEntity.ok(retornoAvaliacaoCliente);

        } catch (DadosClienteNotFoundException  e) {
            return ResponseEntity.notFound().build();
        } catch (ErroComunicacaoMicroservicesException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }

    @PostMapping("solicitacoes-cartao")
    public ResponseEntity solicitarCartao(@RequestBody DadosSolicitacaoEmissaoCartao dados) {
        try {
            ProtocoloSolicitacaoCartao protocoloSolicitacaoCartao = avaliadorCreditoService
                    .solicitacaoEmissaoCartao(dados);

            return ResponseEntity.ok(protocoloSolicitacaoCartao);
        }catch (ErroSolicitacaoCartaoException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
