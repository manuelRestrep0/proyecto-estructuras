package com.manuel.proyecto.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Usuario {

    private int id;
    private String nombre;
    private String nombreUsuario;
    private int peso;

}
