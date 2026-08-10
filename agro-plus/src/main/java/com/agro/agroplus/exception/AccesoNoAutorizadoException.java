package com.agro.agroplus.exception;

public class AccesoNoAutorizadoException extends RuntimeException{

    public AccesoNoAutorizadoException(String mensaje){
        super(mensaje);
    }
}
