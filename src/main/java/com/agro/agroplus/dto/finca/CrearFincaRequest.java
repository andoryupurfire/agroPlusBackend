package com.agro.agroplus.dto.finca;

public record CrearFincaRequest(
        String nombre,
        String ubicacion,
        Double areaTotalHectareas

) {
}
