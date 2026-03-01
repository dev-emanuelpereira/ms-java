package com.github.dev_emanuelpereira.msavaliadorcredito.application.exception;

import lombok.Getter;

public class ErroComunicacaoMicroservicesException extends Exception{

    @Getter
    private Integer status;

    public ErroComunicacaoMicroservicesException(String mensagem, Integer status) {
        super(mensagem);
        this.status = status;
    }
}
