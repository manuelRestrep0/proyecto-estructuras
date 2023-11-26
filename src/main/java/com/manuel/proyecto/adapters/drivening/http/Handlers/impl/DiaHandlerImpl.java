package com.manuel.proyecto.adapters.drivening.http.Handlers.impl;

import com.manuel.proyecto.adapters.drivening.http.Handlers.IDiaHandler;
import com.manuel.proyecto.domain.api.IDiaServicePort;
import com.manuel.proyecto.domain.model.Dia;
import com.manuel.proyecto.domain.model.DiasRecomendados;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiaHandlerImpl implements IDiaHandler {

    private final IDiaServicePort diaServicePort;

    @Override
    public void generarDiasRecomendados(int idUsuario, float peso, int mes) {
        diaServicePort.generarDiasRecomendados(idUsuario, peso, mes);
    }

    @Override
    public void generarDias(int idUsuario, int mes) {
        diaServicePort.generarDiasMes(idUsuario, mes);
    }

    @Override
    public List<DiasRecomendados> obtenerDiasRecomendados(int idUsuario, int mes) {
        return diaServicePort.obtenerDiasRecomendados(idUsuario, mes);
    }

    @Override
    public List<Dia> obtenerDias(int idUsuario, int mes) {
        return diaServicePort.obtenerDias(idUsuario, mes);
    }
}
