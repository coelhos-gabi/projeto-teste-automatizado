package br.com.letscode.projetotesteautomatizado.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class FilmeNotFoundException extends ResponseStatusException {

    public FilmeNotFoundException(String reason) {
        super(HttpStatus.NOT_FOUND, reason);
    }
}
