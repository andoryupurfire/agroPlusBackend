package com.agro.agroplus.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="variedad")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Variedad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;

    @Column(name="dist_plantas", nullable=false)
    private Double distPlantas;

    @Column(name="dist_surcos", nullable=false)
    private Double distSurcos;

    @Column(name="densidad", nullable=false)
    private Integer densidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_cultivo_id", nullable = false)
    private TipoCultivo tipoCultivo;

}
