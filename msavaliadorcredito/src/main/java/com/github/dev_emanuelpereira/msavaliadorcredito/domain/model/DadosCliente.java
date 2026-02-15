package com.github.dev_emanuelpereira.msavaliadorcredito.domain.model;

import lombok.Data;

import java.util.UUID;

@Data
public class DadosCliente {
    private UUID id;
    private String nome;
    private Integer idade;

}
