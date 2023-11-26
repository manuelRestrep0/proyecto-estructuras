package com.manuel.proyecto.adapters.drivening.http.Handlers;

import com.manuel.proyecto.domain.model.Dia;
import com.manuel.proyecto.domain.model.DiasRecomendados;

import java.util.List;

public interface IDiaHandler {

    void generarDiasRecomendados(int idUsuario, float peso, int mes);
    void generarDias(int idUsuario, int mes);
    List<DiasRecomendados> obtenerDiasRecomendados(int idUsuario, int mes);
    List<Dia> obtenerDias(int idUsuario, int mes);
}
