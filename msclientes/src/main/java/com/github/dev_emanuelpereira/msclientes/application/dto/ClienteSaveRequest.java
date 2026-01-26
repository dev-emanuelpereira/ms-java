package com.github.dev_emanuelpereira.msclientes.application.dto;

import com.github.dev_emanuelpereira.msclientes.domain.model.Cliente;

public record ClienteSaveRequest(
        String cpf,
        String nome,
        Integer idade
) {
}
