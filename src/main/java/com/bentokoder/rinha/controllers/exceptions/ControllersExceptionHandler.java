package com.bentokoder.rinha.controllers.exceptions;

import com.bentokoder.rinha.exceptions.ClientNotFoundException;
import com.bentokoder.rinha.exceptions.InsufficientFundsException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllersExceptionHandler {
    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<StandardError> clientNotFound(ClientNotFoundException e, HttpServletRequest request){
        String error = "Cliente não encontrado";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(), e.getMessage(), error, request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(InsufficientFundsException.class)
    public ResponseEntity<StandardError> clientNotFound(InsufficientFundsException e, HttpServletRequest request){
        String error = "Cliente com saldo insuficiente";
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        StandardError err = new StandardError(Instant.now(), status.value(), e.getMessage(), error, request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
