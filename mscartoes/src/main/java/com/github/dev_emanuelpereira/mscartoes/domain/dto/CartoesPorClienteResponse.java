package com.github.dev_emanuelpereira.mscartoes.domain.dto;

import com.github.dev_emanuelpereira.mscartoes.domain.model.BandeiraCartao;
import com.github.dev_emanuelpereira.mscartoes.domain.model.CartaoCliente;

import java.math.BigDecimal;

public record CartoesPorClienteResponse(
        String nome,
        BandeiraCartao bandeira,
        BigDecimal limiteLiberado
) {
    public static CartoesPorClienteResponse fromModel(CartaoCliente cartaoCliente) {
        return new CartoesPorClienteResponse(
                cartaoCliente.getCpf(),
                cartaoCliente.getCartao().getBandeiraCartao(),
                cartaoCliente.getLimite()
        );
    }
}
