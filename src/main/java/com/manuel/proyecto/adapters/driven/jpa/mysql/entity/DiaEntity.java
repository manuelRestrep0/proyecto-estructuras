package com.manuel.proyecto.adapters.driven.jpa.mysql.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "dias")
@Getter
@Setter
public class DiaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDia;
    private int idUsuario;
    private int dia;
    private float aguas;
    private float grasas;
    private float carbos;
    private float proteinas;
    private boolean esRecomendado;

}
