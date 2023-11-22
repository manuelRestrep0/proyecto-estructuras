package com.manuel.proyecto.adapters.drivening.http.Handlers;

import com.manuel.proyecto.domain.model.DiasRecomendados;

import java.util.List;

public interface IAlimentoHandler {

    List<DiasRecomendados> obtenerAlimento(List<String> nombre, int idUsuario, int mes, int dia);
}
