package com.agro.agroplus.repository;


import com.agro.agroplus.entity.EstadoRegistroCultivo;
import com.agro.agroplus.entity.RegistroCultivo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegistroCultivoRepository extends JpaRepository<RegistroCultivo, Long> {

    Optional<RegistroCultivo> findByLoteIdAndEstado(Long loteId, EstadoRegistroCultivo estado);
}
