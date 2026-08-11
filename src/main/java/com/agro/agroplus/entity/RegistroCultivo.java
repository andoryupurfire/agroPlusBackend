package com.agro.agroplus.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@Table(name = "registro_cultivo")
public class RegistroCultivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate fechaSiembra;

    @Enumerated(EnumType.STRING)
    private EstadoRegistroCultivo estado;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="lote_id", nullable=false)
    private Lote lote;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="variedad_id", nullable=false)
    private Variedad variedad;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="etapa_id", nullable=false)
    private EtapaFenologica etapaActual;

}
