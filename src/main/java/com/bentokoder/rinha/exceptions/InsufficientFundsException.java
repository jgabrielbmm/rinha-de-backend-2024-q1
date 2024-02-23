package com.bentokoder.rinha.exceptions;

public class InsufficientFundsException extends RuntimeException{
    public InsufficientFundsException() {
        super("Saldo insuficiente.");
    }
}
