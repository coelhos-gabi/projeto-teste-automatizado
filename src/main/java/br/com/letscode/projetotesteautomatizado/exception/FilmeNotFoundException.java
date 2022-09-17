package br.com.letscode.projetotesteautomatizado.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

public class FilmeNotFoundException extends ResponseStatusException {

    public FilmeNotFoundException(String reason) {
        super(NOT_FOUND, reason);
    }
}
