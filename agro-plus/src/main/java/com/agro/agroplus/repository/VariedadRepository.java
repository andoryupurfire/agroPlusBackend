package com.agro.agroplus.repository;

import com.agro.agroplus.entity.Variedad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VariedadRepository extends JpaRepository<Variedad, Long> {

    List<Variedad> findByTipoCultivoId(Long tipoCultivoId);
}
