package com.github.dev_emanuelpereira.msavaliadorcredito.application.exception;

public class ErroSolicitacaoCartaoException extends RuntimeException{
    public ErroSolicitacaoCartaoException(String mensagem) {
        super(mensagem);
    }
}
