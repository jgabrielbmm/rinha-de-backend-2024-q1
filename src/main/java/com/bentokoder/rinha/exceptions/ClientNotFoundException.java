package com.bentokoder.rinha.exceptions;

public class ClientNotFoundException extends RuntimeException{
    public ClientNotFoundException(Integer id) {
        super("Cliente com o id = " + id + " não encontrado." );
    }
}
