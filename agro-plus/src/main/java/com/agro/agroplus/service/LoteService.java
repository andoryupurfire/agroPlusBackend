package com.agro.agroplus.service;

import com.agro.agroplus.dto.lote.CrearLoteRequest;
import com.agro.agroplus.dto.lote.LoteResponse;
import com.agro.agroplus.entity.Agricultor;
import com.agro.agroplus.entity.Finca;
import com.agro.agroplus.entity.Lote;
import com.agro.agroplus.repository.AgricultorRepository;
import com.agro.agroplus.repository.FincaRepository;
import com.agro.agroplus.repository.LoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoteService {

    private final LoteRepository loteRepository;
    private final FincaRepository fincaRepository;
    private final AgricultorRepository agricultorRepository;

    public LoteResponse crearLote(CrearLoteRequest request){
        String username = obtenerUsernameAutenticado();

        Agricultor agricultor = agricultorRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Agricultor no encontrado"));

        Finca finca = fincaRepository.findById(request.fincaId())
                .orElseThrow(() -> new RuntimeException("Finca no encontrada"));

        if(!finca.getAgricultor().getId().equals(agricultor.getId())){
            throw new RuntimeException("No tienes permiso sobre esa finca");
        }

        Lote lote = Lote.builder()
                .nombre(request.nombre())
                .tamanoHectareas(request.tamanoHectareas())
                .finca(finca)
                .build();

        Lote loteGuardado = loteRepository.save(lote);

        return mapToResponse(loteGuardado);

    }

    //Para listar los lotes
    public List<LoteResponse> listarMisLotes(){
        String username = obtenerUsernameAutenticado();

        return loteRepository.findByFincaId_Agricultor_Username(username)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }


    private String obtenerUsernameAutenticado(){
        return SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();
    }

    private LoteResponse mapToResponse(Lote lote) {
        return new LoteResponse(
                lote.getId(),
                lote.getNombre(),
                lote.getTamanoHectareas(),
                lote.getFinca().getId()
        );
    }
}
