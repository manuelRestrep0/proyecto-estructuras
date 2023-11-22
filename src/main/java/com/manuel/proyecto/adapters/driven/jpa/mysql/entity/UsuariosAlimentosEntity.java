package com.manuel.proyecto.adapters.driven.jpa.mysql.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "usuarios_alimentos")
@Getter
@Setter
public class UsuariosAlimentosEntity {

    @Id
    private int id;
    private int usuario;
    private int alimento;
    private int mes;
    private int dia;
}
