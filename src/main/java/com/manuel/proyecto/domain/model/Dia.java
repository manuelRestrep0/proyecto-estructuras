package com.manuel.proyecto.domain.model;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Dia {
    private int usuario;
    private int dia;
    private int mes;
    private float total_aguas;
    private float total_grasas;
    private float total_proteinas;
    private float total_carbos;
}
