package com.example.demo.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class SymbolNotFoundException extends RuntimeException {
    public SymbolNotFoundException() {
        super();
    }
    public SymbolNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    public SymbolNotFoundException(String message) {
        super(message);
    }
    public SymbolNotFoundException(Throwable cause) {
        super(cause);
    }
}
