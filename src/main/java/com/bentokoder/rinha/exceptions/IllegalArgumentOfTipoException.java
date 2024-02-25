package com.bentokoder.rinha.exceptions;

public class IllegalArgumentOfTipoException extends RuntimeException{
    public IllegalArgumentOfTipoException() {
        super("O argumento do tipo só pode receber os valores 'c' ou 'd'.");
    }
}
