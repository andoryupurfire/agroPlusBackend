package com.agro.agroplus.dto.registroCultivo;

import com.agro.agroplus.entity.EstadoRegistroCultivo;

import java.time.LocalDate;

public record RegistroCultivoResponse(
        Long id,
        LocalDate fechaSiembra,
        EstadoRegistroCultivo estado,
        Long loteId,
        Long variedadId,
        Long etapaId
) {
}
