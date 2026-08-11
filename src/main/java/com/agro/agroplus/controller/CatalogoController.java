package com.agro.agroplus.controller;


import com.agro.agroplus.dto.catalogo.EtapaFenologicaResponse;
import com.agro.agroplus.dto.catalogo.TipoCultivoResponse;
import com.agro.agroplus.dto.catalogo.VariedadResponse;
import com.agro.agroplus.service.CatalogoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/catalogos")
@RequiredArgsConstructor
public class CatalogoController {

    private final CatalogoService catalogoService;

    @GetMapping("/cultivos")
    public List<TipoCultivoResponse> listarCultivos(){
        return catalogoService.listarCultivos();
    }

    @GetMapping("/cultivos/{cultivoId}/variedades")
    public List<VariedadResponse> listarVariedades(
            @PathVariable Long cultivoId
    ){
        return catalogoService.listarVariedades(cultivoId);
    }

    @GetMapping("/cultivos/{cultivoId}/etapas")
    public List<EtapaFenologicaResponse> listarEtapas(
            @PathVariable Long cultivoId
    ){
       return catalogoService.listarEtapas(cultivoId);
    }

}
