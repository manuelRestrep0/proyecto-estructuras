package com.manuel.proyecto.domain.spi;

import com.manuel.proyecto.domain.model.Dia;

import java.util.List;

public interface DiaPersistencePort {

    List<Dia> obtenerDiasRecomendados(int idUsuario);
    void guardarDiasRecomendados(List<Dia> dias);
}
