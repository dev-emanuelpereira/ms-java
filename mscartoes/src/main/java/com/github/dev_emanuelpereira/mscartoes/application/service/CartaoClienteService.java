package com.github.dev_emanuelpereira.mscartoes.application.service;

import com.github.dev_emanuelpereira.mscartoes.domain.model.CartaoCliente;
import com.github.dev_emanuelpereira.mscartoes.infra.repository.CartaoClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartaoClienteService {
    private final CartaoClienteRepository repository;

    public List<CartaoCliente> listCartoesByCpf(String cpf){
        return repository.findByCpf(cpf);
    }
}
