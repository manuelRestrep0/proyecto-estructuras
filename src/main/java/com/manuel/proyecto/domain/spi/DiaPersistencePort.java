package com.manuel.proyecto.domain.spi;

import com.manuel.proyecto.domain.model.Dia;
import com.manuel.proyecto.domain.model.DiasRecomendados;

import java.util.List;

public interface DiaPersistencePort {

    List<DiasRecomendados> obtenerDiasRecomendados(int idUsuario, int mes);
    List<Dia> obtenerDiasIngresados(int idUsuario, int mes);
    void guardarDiasRecomendados(List<DiasRecomendados> dias);
    void guardarTotalDias(List<Dia> dias);
}
