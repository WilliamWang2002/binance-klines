package com.example.demo.controller;

import com.example.demo.model.LoadResult;
import com.example.demo.model.exception.SymbolNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value
            = { SymbolNotFoundException.class, IllegalStateException.class })
    protected ResponseEntity<Object> handleConflict(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Error";
        LoadResult result = new LoadResult(bodyOfResponse, ex);
        return handleExceptionInternal(ex, ex,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}
