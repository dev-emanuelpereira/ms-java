package com.github.dev_emanuelpereira.mscartoes.domain.dto;

import com.github.dev_emanuelpereira.mscartoes.domain.model.BandeiraCartao;

import java.math.BigDecimal;

public record CartaoSaveRequest(
        String nome,
        BandeiraCartao bandeira,
        BigDecimal renda,
        BigDecimal limite
) {
}
