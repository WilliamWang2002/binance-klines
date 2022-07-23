package com.example.demo.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class RunNameCollisionException extends RuntimeException {
    public RunNameCollisionException() {
        super();
    }
    public RunNameCollisionException(String message, Throwable cause) {
        super(message, cause);
    }
    public RunNameCollisionException(String message) {
        super(message);
    }
    public RunNameCollisionException(Throwable cause) {
        super(cause);
    }
}
