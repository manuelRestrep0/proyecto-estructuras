package com.manuel.proyecto.adapters.driven.jpa.mysql.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "dias_recomendados")
@Getter
@Setter
@ToString
public class DiasRecomendadosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_dias_recomendados;
    private int usuario;
    private float aguas;
    private float grasas;
    private float proteinas;
    private float carbos;
    private int dia;
    private int mes;
}
