package com.github.dev_emanuelpereira.msavaliadorcredito.infra.clients;

import com.github.dev_emanuelpereira.msavaliadorcredito.domain.model.DadosCliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "msclientes", path = "/clientes")
public interface ClienteResourceClient {

    @GetMapping(params = "cpf")
    ResponseEntity<DadosCliente> dadosClientes(@RequestParam("cpf") String cpf);

}
