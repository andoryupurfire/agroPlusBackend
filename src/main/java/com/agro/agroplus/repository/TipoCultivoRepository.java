package com.agro.agroplus.repository;

import com.agro.agroplus.entity.TipoCultivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoCultivoRepository extends JpaRepository <TipoCultivo, Long> {


}
