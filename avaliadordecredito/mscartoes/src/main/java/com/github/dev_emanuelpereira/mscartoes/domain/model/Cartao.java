package com.github.dev_emanuelpereira.mscartoes.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
@Table(name = "cartoes")
@NoArgsConstructor
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column
    private String nome;
    @Column
    @Enumerated(EnumType.STRING)
    private BandeiraCartao bandeiraCartao;
    @Column
    private BigDecimal renda;
    @Column
    private BigDecimal limiteBasico;
}
