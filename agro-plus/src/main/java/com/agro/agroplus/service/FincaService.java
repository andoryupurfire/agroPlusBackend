package com.agro.agroplus.service;

import com.agro.agroplus.dto.finca.CrearFincaRequest;
import com.agro.agroplus.dto.finca.FincaResponse;
import com.agro.agroplus.entity.Agricultor;
import com.agro.agroplus.entity.Finca;
import com.agro.agroplus.repository.AgricultorRepository;
import com.agro.agroplus.repository.FincaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FincaService {

    private final FincaRepository fincaRepository;
    private final AgricultorRepository agricultorRepository;

    public FincaResponse crearFinca(CrearFincaRequest request){
        String username = obtenerUsernameAutenticado();

        Agricultor agricultor = agricultorRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Agricultor no encontrado"));

        Finca finca = Finca.builder()
                .nombre(request.nombre())
                .ubicacion(request.ubicacion())
                .areaTotalHectareas(request.areaTotalHectareas())
                .agricultor(agricultor)
                .build();

        Finca fincaGuardada = fincaRepository.save(finca);

        return mapToResponse(fincaGuardada);
    }


    public List<FincaResponse> listarMisFincas(){
        String username = obtenerUsernameAutenticado();

        return fincaRepository.findByAgricultorUsername(username)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private String obtenerUsernameAutenticado(){
        return SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();
    }

    private FincaResponse mapToResponse(Finca finca){
        return new FincaResponse(
                finca.getId(),
                finca.getNombre(),
                finca.getUbicacion(),
                finca.getAreaTotalHectareas()
        );
    }


}
