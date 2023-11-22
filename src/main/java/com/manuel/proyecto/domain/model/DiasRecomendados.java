package com.manuel.proyecto.domain.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DiasRecomendados {
    private int id_dias_recomendados;

    private int usuario;
    private float grasas;
    private float proteinas;
    private float aguas;
    private float carbos;
    private int mes;
    private int dia;
}
