package com.example.demo.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class InvalidTimeException extends RuntimeException {
    public InvalidTimeException() {
        super();
    }
    public InvalidTimeException(String message, Throwable cause) {
        super(message, cause);
    }
    public InvalidTimeException(String message) {
        super(message);
    }
    public InvalidTimeException(Throwable cause) {
        super(cause);
    }
}
