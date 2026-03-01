package com.github.dev_emanuelpereira.msclientes.infra.repository;

import com.github.dev_emanuelpereira.msclientes.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
    Optional<Cliente> findFirstByCpf(String cpf);
}
