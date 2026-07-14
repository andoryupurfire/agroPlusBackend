package com.agro.agroplus.entity;

//Imports
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//Anotation for Entities
@Entity
@Table(name = "agricultor")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Agricultor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String nombre;

    @Column(nullable=false, unique = true)
    private String username;

    @Column(nullable=false)
    private String password;

}
