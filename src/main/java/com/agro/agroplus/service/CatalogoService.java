package com.agro.agroplus.service;


import com.agro.agroplus.dto.catalogo.EtapaFenologicaResponse;
import com.agro.agroplus.dto.catalogo.TipoCultivoResponse;
import com.agro.agroplus.dto.catalogo.VariedadResponse;
import com.agro.agroplus.repository.EtapaFenologicaRepository;
import com.agro.agroplus.repository.TipoCultivoRepository;
import com.agro.agroplus.repository.VariedadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CatalogoService {

    private final TipoCultivoRepository tipoCultivoRepository;
    private final VariedadRepository variedadRepository;
    private final EtapaFenologicaRepository etapaRepository;

    public List<TipoCultivoResponse> listarCultivos() {
        return tipoCultivoRepository.findAll()
                .stream()
                .map(cultivo -> new TipoCultivoResponse(
                        cultivo.getId(),
                        cultivo.getNombre(),
                        cultivo.getTipoCiclo()
                ))
                .toList();
    }

    public List<VariedadResponse> listarVariedades(long idCultivo) {
        return variedadRepository.findByTipoCultivoId(idCultivo)
                .stream()
                .map(variedad -> new VariedadResponse(
                        variedad.getId(),
                        variedad.getNombre(),
                        variedad.getDistPlantas(),
                        variedad.getDistSurcos(),
                        variedad.getDensidad()
                ))
                .toList();
    }

    public List<EtapaFenologicaResponse> listarEtapas(long idCultivo){
        return etapaRepository.findByTipoCultivoIdOrderByOrdenAsc(idCultivo)
                .stream()
                .map(etapaFenologica -> new EtapaFenologicaResponse(
                        etapaFenologica.getId(),
                        etapaFenologica.getNombre(),
                        etapaFenologica.getOrden(),
                        etapaFenologica.getDuracionMinimaDia(),
                        etapaFenologica.getDuracionMaximaDia()
                ))
                .toList();
    }
}
