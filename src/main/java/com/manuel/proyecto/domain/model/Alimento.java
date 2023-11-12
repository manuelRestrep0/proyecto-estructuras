package com.manuel.proyecto.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Alimento {

    private String nombre;
    private String nombreCorto;
    private float agua;
    private float proteina;
    private float grasas;
    private float carbos;
}
