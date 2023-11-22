package com.manuel.proyecto.adapters.driven.jpa.mysql.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "alimentos")
@Getter
@Setter
public class AlimentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private float aguas;
    private float grasas;
    private float carbos;
    private float proteina;
}
