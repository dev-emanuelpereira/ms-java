package com.github.dev_emanuelpereira.msclientes.application.service;

import com.github.dev_emanuelpereira.msclientes.domain.model.Cliente;
import com.github.dev_emanuelpereira.msclientes.infra.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;

    public Cliente save(Cliente cliente) {
        return repository.save(cliente);
    }

    public Optional<Cliente> obterDadosCliente(String cpf) {
        return repository.findFirstByCpf(cpf);
    }
}
