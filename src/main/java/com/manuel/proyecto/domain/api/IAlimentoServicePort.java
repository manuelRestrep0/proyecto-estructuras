package com.manuel.proyecto.domain.api;

import com.manuel.proyecto.domain.model.Alimento;
import com.manuel.proyecto.domain.model.DiasRecomendados;

import java.util.List;

public interface IAlimentoServicePort {

    List<DiasRecomendados> obtenerInformacion(List<String> nombre, int idUsuario, int mes, int dia);
    List<Alimento> obtenerAlimentos();
}
