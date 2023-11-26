package com.manuel.proyecto.domain.api;


import com.manuel.proyecto.domain.model.Dia;
import com.manuel.proyecto.domain.model.DiasRecomendados;

import java.util.List;

public interface IDiaServicePort {

    void generarDiasRecomendados(int idUsuario, float peso, int mes);
    void generarDiasMes(int idUsuario, int mes);
    List<DiasRecomendados> obtenerDiasRecomendados(int idUsuario, int mes);
    List<Dia> obtenerDias(int idUsuario, int mes);
}
