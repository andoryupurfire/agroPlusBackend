package com.agro.agroplus.service;


import com.agro.agroplus.dto.registroCultivo.CrearRegistroCultivoRequest;
import com.agro.agroplus.dto.registroCultivo.RegistroCultivoResponse;
import com.agro.agroplus.entity.*;
import com.agro.agroplus.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegistroCultivoService {

    private final RegistroCultivoRepository registroCultivoRepository;
    private final LoteRepository loteRepository;
    private final AgricultorRepository agricultorRepository;
    private final VariedadRepository variedadRepository;
    private final EtapaFenologicaRepository etapaFenologicaRepository;

    public RegistroCultivoResponse crearRegistroCultivo (CrearRegistroCultivoRequest request){
        String username = obtenerUsernameAutenticado();

        //Validar si el usuario autenticado existe
        Agricultor agricultor = agricultorRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Agricultor no encontrado"));

        //Buscar el lote por el lote id donde se esta creando el cultivo
        Lote lote = loteRepository.findById(request.loteId())
                .orElseThrow(() -> new RuntimeException("Lote no encontrado"));

        //Validamos si el lote le pertence al agricultor
        if(!lote.getFinca().getAgricultor().getId().equals(agricultor.getId())){
            throw new RuntimeException("No tienes permiso sobre ese lote");
        }

        //Buscamos si ya existe un registro activo para este lote
        Optional<RegistroCultivo>registroActivo = registroCultivoRepository.findByLoteIdAndEstado(request.loteId(), EstadoRegistroCultivo.ACTIVO);

        if(registroActivo.isPresent()){
            throw new RuntimeException("Este lote ya tiene un cultivo activo");
        }

        //Buscamos la variedad por variedad id
        Variedad variedad = variedadRepository.findById(request.variedadId())
                .orElseThrow(() -> new RuntimeException("Variedad  no encontrado"));

        //Buscamos la etapa fenologica de orden 1 para esa variedad

        //Navegamos de variedad a tipo de Cultivo
        TipoCultivo tipoCultivo = variedad.getTipoCultivo();

        EtapaFenologica etapaInicial = etapaFenologicaRepository.findByTipoCultivoIdAndOrden(tipoCultivo.getId(),1)
                .orElseThrow(()->new RuntimeException("No se encontro la etapa inicial para este cultivo"));

        RegistroCultivo registroCultivo = RegistroCultivo.builder()
                .fechaSiembra(request.fechaSiembra())
                .estado(EstadoRegistroCultivo.ACTIVO)
                .lote(lote)
                .variedad(variedad)
                .etapaActual(etapaInicial)
                .build();

        RegistroCultivo registroCultivoGuardado = registroCultivoRepository.save(registroCultivo);


        return mapToResponse(registroCultivoGuardado);
    }


    //Metodo para obtener usuario autenticado
    private String obtenerUsernameAutenticado(){
        return SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();
    }

    private RegistroCultivoResponse mapToResponse(RegistroCultivo registroCultivo){
        return new RegistroCultivoResponse(
                registroCultivo.getId(),
                registroCultivo.getFechaSiembra(),
                registroCultivo.getEstado(),
                registroCultivo.getLote().getId(),
                registroCultivo.getVariedad().getId(),
                registroCultivo.getEtapaActual().getId()
        );
    }

}
