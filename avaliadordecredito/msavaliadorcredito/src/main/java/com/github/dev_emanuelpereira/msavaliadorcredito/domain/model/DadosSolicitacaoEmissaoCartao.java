package com.github.dev_emanuelpereira.msavaliadorcredito.domain.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class DadosSolicitacaoEmissaoCartao {
    private UUID idCartao;
    private String cpf;
    private String endereco;
    private BigDecimal limiteLiberado;
}
