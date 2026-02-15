package com.github.dev_emanuelpereira.msclientes.application.controller;

import com.github.dev_emanuelpereira.msclientes.application.dto.ClienteSaveRequest;
import com.github.dev_emanuelpereira.msclientes.application.service.ClienteService;
import com.github.dev_emanuelpereira.msclientes.domain.model.Cliente;
import com.github.dev_emanuelpereira.msclientes.infra.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService service;

    @PostMapping
    public ResponseEntity save(@RequestBody ClienteSaveRequest request) {
        Cliente cliente = new Cliente();
        cliente.setCpf(request.cpf());
        cliente.setNome(request.nome());
        cliente.setIdade(request.idade());

        service.save(cliente);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(cliente.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping(params = "cpf")
    public ResponseEntity findClienteByCpf (@RequestParam String cpf) {
        var cliente = service.obterDadosCliente(cpf);

        if (cliente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(cliente);


    }
}
