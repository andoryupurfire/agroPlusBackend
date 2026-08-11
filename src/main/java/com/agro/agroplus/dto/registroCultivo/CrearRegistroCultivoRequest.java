package com.agro.agroplus.dto.registroCultivo;

import com.agro.agroplus.entity.EstadoRegistroCultivo;
import java.time.LocalDate;

public record CrearRegistroCultivoRequest(
        LocalDate fechaSiembra,
        Long loteId,
        Long variedadId
) {
}
