package com.agro.agroplus.repository;

import com.agro.agroplus.entity.Agricultor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgricultorRepository extends JpaRepository<Agricultor, Long> {

    Optional<Agricultor> findByUsername(String username);
    boolean existsByUsername(String username);
}

