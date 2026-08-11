package com.agro.agroplus.entity;


import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@Entity
@Table(name = "lote")
public class Lote {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String nombre;

    @Column(nullable=false)
    private Double tamanoHectareas;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="finca_id", nullable=false)
    private Finca finca;
}
