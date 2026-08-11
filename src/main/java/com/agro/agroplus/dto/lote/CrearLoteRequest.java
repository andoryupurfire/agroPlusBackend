package com.agro.agroplus.dto.lote;

import com.agro.agroplus.entity.Finca;

public record CrearLoteRequest(

        String nombre,
        Double tamanoHectareas,
        Long fincaId
) {
}
