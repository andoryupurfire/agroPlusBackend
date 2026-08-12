package com.agro.agroplus.repository;


import com.agro.agroplus.entity.Lote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoteRepository extends JpaRepository<Lote, Long> {
    List<Lote>findByFincaId(Long fincaId);

    List<Lote>findByFincaId_Agricultor_Username(String username);

}
