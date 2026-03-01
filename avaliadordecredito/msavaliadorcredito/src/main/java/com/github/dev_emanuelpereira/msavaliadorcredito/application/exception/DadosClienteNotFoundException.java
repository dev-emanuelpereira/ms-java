package com.github.dev_emanuelpereira.msavaliadorcredito.application.exception;

public class DadosClienteNotFoundException extends Exception {
    public DadosClienteNotFoundException(){
        super("Dados cliente não retornado para o CPF informado.");
    }
}
