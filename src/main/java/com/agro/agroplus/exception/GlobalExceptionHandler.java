package com.agro.agroplus.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //404 Recurso no encontrado
    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<String> handleNoEncontrado(RecursoNoEncontradoException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    //403 Acceso No Autorizado
    @ExceptionHandler(AccesoNoAutorizadoException.class)
    public ResponseEntity<String> handleNoAutorizado(AccesoNoAutorizadoException ex){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
    }

    //409 Regla de negocio Conflictos
    @ExceptionHandler(ReglaDeNegocioException.class)
    public ResponseEntity<String> handlerReglaNegocio(ReglaDeNegocioException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    //Esta es una excepcion generica
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }


}
