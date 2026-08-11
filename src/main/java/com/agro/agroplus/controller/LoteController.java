package com.agro.agroplus.controller;


import com.agro.agroplus.dto.lote.CrearLoteRequest;
import com.agro.agroplus.dto.lote.LoteResponse;
import com.agro.agroplus.service.LoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lotes")
@RequiredArgsConstructor
public class LoteController {

    private final LoteService loteService;

    //Endpoint para crear el Lote (POST)
    @PostMapping
    public ResponseEntity<LoteResponse> crearLote(@RequestBody CrearLoteRequest request){
        LoteResponse response = loteService.crearLote(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //Endpoint para listar los lotes (GET)
    @GetMapping
    public List<LoteResponse> listarMisLotes(){
        return loteService.listarMisLotes();
    }
}
