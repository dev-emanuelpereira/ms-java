package com.github.dev_emanuelpereira.mscartoes.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table
@Data
@NoArgsConstructor
public class CartaoCliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String cpf;
    @ManyToOne
    @JoinColumn(name = "id_cartao")
    private Cartao cartao;
    private BigDecimal limite;
}
