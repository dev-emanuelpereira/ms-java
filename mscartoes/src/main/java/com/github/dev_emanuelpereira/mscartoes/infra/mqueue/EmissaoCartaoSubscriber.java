package com.github.dev_emanuelpereira.mscartoes.infra.mqueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dev_emanuelpereira.mscartoes.domain.model.Cartao;
import com.github.dev_emanuelpereira.mscartoes.domain.model.CartaoCliente;
import com.github.dev_emanuelpereira.mscartoes.domain.model.DadosSolicitacaoEmissaoCartao;
import com.github.dev_emanuelpereira.mscartoes.infra.repository.CartaoClienteRepository;
import com.github.dev_emanuelpereira.mscartoes.infra.repository.CartaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EmissaoCartaoSubscriber {

    private final CartaoRepository cartaoRepository;
    private final CartaoClienteRepository cartaoClienteRepository;

    @RabbitListener(queues = "${mq.queues.emissao-cartoes}")
    public void receberSolicitacaoEmissao(@Payload String payload) {
        try {
            var mapper = new ObjectMapper();
            DadosSolicitacaoEmissaoCartao dadosSolicitacaoEmissaoCartao = mapper.readValue(payload, DadosSolicitacaoEmissaoCartao.class);
            Cartao cartao = cartaoRepository.findById(dadosSolicitacaoEmissaoCartao.getIdCartao())
                    .orElseThrow();

            CartaoCliente cartaoCliente = new CartaoCliente();
            cartaoCliente.setCartao(cartao);
            cartaoCliente.setCpf(dadosSolicitacaoEmissaoCartao.getCpf());
            cartaoCliente.setLimite(dadosSolicitacaoEmissaoCartao.getLimiteLiberado());

            cartaoClienteRepository.save(cartaoCliente);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

}
