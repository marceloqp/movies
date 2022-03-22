package com.pinheiro.movies.exception;

import org.springframework.http.HttpStatus;

public class RegistroNaoEncontradoException extends GenericRestException {

    public RegistroNaoEncontradoException(String idRegistro) {
        super(HttpStatus.NOT_FOUND,"O registro de código " + idRegistro + " não foi encontrado(a).");
    }
}
