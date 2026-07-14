package com.agro.agroplus.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="tipo_cultivo")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TipoCultivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;

    @Column(name = "tipo_ciclo", nullable = false)
    private String tipoCiclo; //Anual o perenne

    @OneToMany(mappedBy = "tipoCultivo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Variedad> variedades;

    @OneToMany(mappedBy = "tipoCultivo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EtapaFenologica> etapasFenologicas;
}
