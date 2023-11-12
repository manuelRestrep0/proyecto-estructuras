package com.manuel.proyecto.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Usuario {

    private int id;
    private String nombre;
    private String nombreUsuario;
    private int peso;

}
