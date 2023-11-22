package com.manuel.proyecto.adapters.driven.jpa.mysql.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "total_diario_usuario")
@Getter
@Setter
@ToString
public class TotalDiarioUsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int usuario;
    private float total_grasas;
    private float total_aguas;
    private float total_proteinas;
    private float total_carbos;
    private int mes;
    private int dia;
}
