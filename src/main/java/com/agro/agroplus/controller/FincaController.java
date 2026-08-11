package com.agro.agroplus.controller;


import com.agro.agroplus.dto.finca.CrearFincaRequest;
import com.agro.agroplus.dto.finca.FincaResponse;
import com.agro.agroplus.service.FincaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fincas")
@RequiredArgsConstructor
public class FincaController {

    private final FincaService fincaService;

    @PostMapping
    public ResponseEntity<FincaResponse> crearFinca(@RequestBody CrearFincaRequest request){
        FincaResponse response = fincaService.crearFinca(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }



    @GetMapping
    public List< FincaResponse> listarMisFincas() {
        return fincaService.listarMisFincas();
    }

}
