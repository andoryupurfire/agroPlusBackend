package com.agro.agroplus.dto.lote;

import com.agro.agroplus.entity.Finca;

public record LoteResponse(
        Long id,
        String nombre,
        Double tamanoHectareas,
        Long fincaId

) {
}
