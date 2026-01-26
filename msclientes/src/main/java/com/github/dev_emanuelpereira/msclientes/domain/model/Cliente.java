package com.github.dev_emanuelpereira.msclientes.domain.model;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@Entity
@Table(name = "cliente", schema = "public")
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column
    private String nome;
    @Column
    private String cpf;
    @Column
    private Integer idade;

}
