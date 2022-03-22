package com.pinheiro.movies.exception;

import org.springframework.http.HttpStatus;

public class RegistroVinculadoException extends GenericRestException {

    public RegistroVinculadoException(Class<?> classe) {
        super(HttpStatus.BAD_REQUEST, classe.getSimpleName() +
                " está vinculado(a) em outros cadastros e não pode ser excluído(a)!");
    }

}
