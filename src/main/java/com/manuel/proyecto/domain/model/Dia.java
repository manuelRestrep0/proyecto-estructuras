package com.manuel.proyecto.domain.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Dia {
    private int idUsuario;
    private int dia;
    private float aguas;
    private float grasas;
    private float proteinas;
    private float carbos;
    private boolean esRecomendado;
}
