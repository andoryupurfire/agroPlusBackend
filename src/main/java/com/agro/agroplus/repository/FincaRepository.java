package com.agro.agroplus.repository;

import com.agro.agroplus.entity.Finca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface FincaRepository extends JpaRepository<Finca, Long> {
    List<Finca> findByAgricultorUsername(String username);
}
