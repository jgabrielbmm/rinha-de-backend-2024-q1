package com.bentokoder.rinha.entidade;

public enum Tipos {
    D('d'), C('c');

    private char code;

    private Tipos(char code){
        this.code = code;
    }

    public char getCode() {
        return code;
    }

    public static Tipos obterValorDoTipo(char code){
        for(Tipos tipo : Tipos.values()){
            if(tipo.getCode() == code)
                return tipo;
        }

        throw new IllegalArgumentException("Tipo invalido");
    }

}
