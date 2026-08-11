package com.agro.agroplus.repository;

import com.agro.agroplus.entity.EtapaFenologica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EtapaFenologicaRepository extends JpaRepository<EtapaFenologica, Long>  {

    List<EtapaFenologica>findByTipoCultivoIdOrderByOrdenAsc(Long tipoCultivoId);
    Optional<EtapaFenologica> findByTipoCultivoIdAndOrden(Long tipoCultivoId, Integer orden);
}
