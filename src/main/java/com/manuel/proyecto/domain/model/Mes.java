package com.manuel.proyecto.domain.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Mes {

    private int idUsuario;
    private float aguasTotales;
    private float grasasTotales;
    private float proteinasTotales;
    private float carbosTotales;
}
