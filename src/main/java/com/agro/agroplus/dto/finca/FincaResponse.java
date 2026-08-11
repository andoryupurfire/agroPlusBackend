package com.agro.agroplus.dto.finca;

public record FincaResponse(
    Long id,
    String nombre,
    String ubicacion,
    Double areaTotalHectareas

) {
}
