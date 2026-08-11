package com.agro.agroplus.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="etapa_fenologica")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EtapaFenologica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(name = "orden", nullable = false)
    private Integer orden;

    @Column(name = "dur_min_dia", nullable = false)
    private Integer duracionMinimaDia;

    @Column(name = "dur_max_dia", nullable = false)
    private Integer duracionMaximaDia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_cultivo_id", nullable = false)
    private TipoCultivo tipoCultivo;

}
