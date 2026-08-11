package com.agro.agroplus.controller;


import com.agro.agroplus.dto.registroCultivo.CrearRegistroCultivoRequest;
import com.agro.agroplus.dto.registroCultivo.RegistroCultivoResponse;
import com.agro.agroplus.service.RegistroCultivoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/registroCultivo")
@RequiredArgsConstructor
public class RegistroCultivoController {

    private final RegistroCultivoService registroCultivoService;

    //Endpoint para crear el cultivo
    @PostMapping
    public ResponseEntity<RegistroCultivoResponse> crearRegistroCultivo(
            @RequestBody CrearRegistroCultivoRequest request){

        RegistroCultivoResponse response = registroCultivoService.crearRegistroCultivo(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //Metodo para avanzar en la etapa Fenologica
    @PatchMapping("/{registroCultivoId}/avanzar-etapa")
    public ResponseEntity<RegistroCultivoResponse> avanzarEtapaFenologica(
            @PathVariable Long registroCultivoId
    ){
        RegistroCultivoResponse response = registroCultivoService.avanzarEtapaFenologica(registroCultivoId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


}
