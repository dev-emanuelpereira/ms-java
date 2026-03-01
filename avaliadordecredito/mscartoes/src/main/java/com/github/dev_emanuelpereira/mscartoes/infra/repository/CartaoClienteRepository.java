package com.github.dev_emanuelpereira.mscartoes.infra.repository;

import com.github.dev_emanuelpereira.mscartoes.domain.model.CartaoCliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CartaoClienteRepository extends JpaRepository<CartaoCliente, UUID> {
    List<CartaoCliente> findByCpf(String cpf);

}
