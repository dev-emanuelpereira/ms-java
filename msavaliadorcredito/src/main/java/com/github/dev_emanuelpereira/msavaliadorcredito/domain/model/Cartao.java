package com.github.dev_emanuelpereira.msavaliadorcredito.domain.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class Cartao {
    private UUID id;
    private String nome;
    private String bandeira;
    private BigDecimal limiteBasico;
}
