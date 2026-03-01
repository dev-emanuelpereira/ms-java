package com.github.dev_emanuelpereira.mscartoes.application.controller;

import com.github.dev_emanuelpereira.mscartoes.application.service.CartaoClienteService;
import com.github.dev_emanuelpereira.mscartoes.application.service.CartaoService;
import com.github.dev_emanuelpereira.mscartoes.domain.dto.CartaoSaveRequest;
import com.github.dev_emanuelpereira.mscartoes.domain.dto.CartoesPorClienteResponse;
import com.github.dev_emanuelpereira.mscartoes.domain.model.Cartao;
import com.github.dev_emanuelpereira.mscartoes.domain.model.CartaoCliente;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("cartoes")
@RequiredArgsConstructor
public class CartoesController {

    private final CartaoService cartaoService;
    private final CartaoClienteService cartaoClienteService;

    @PostMapping
    public ResponseEntity cadastra (@RequestBody CartaoSaveRequest request) {
        Cartao cartao = new Cartao();
        cartao.setNome(request.nome());
        cartao.setBandeiraCartao(request.bandeira());
        cartao.setRenda(request.renda());
        cartao.setLimiteBasico(request.limite());

        cartaoService.save(cartao);

        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @GetMapping(params = "renda")
    public ResponseEntity<List<Cartao>> getCartaoRendaAte(@RequestParam("renda") Long renda) {
        List<Cartao> list = cartaoService.getCartoesRendaMenorIgual(renda);
        return ResponseEntity.ok(list);
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<List<CartoesPorClienteResponse>> getCartoesByCliente(@RequestParam("cpf") String cpf){
        List<CartaoCliente> list = cartaoClienteService.listCartoesByCpf(cpf);
        List<CartoesPorClienteResponse> listCartoes = list.stream().map(CartoesPorClienteResponse::fromModel).collect(Collectors.toList());
        return ResponseEntity.ok(listCartoes);
    }
}
