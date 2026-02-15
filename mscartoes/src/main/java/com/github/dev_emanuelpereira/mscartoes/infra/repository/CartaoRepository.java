package com.github.dev_emanuelpereira.mscartoes.infra.repository;

import com.github.dev_emanuelpereira.mscartoes.domain.model.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface CartaoRepository extends JpaRepository<Cartao, UUID> {
    List<Cartao> findByRendaLessThanEqual(BigDecimal rendaBigDecimal);
}
