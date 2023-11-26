package com.manuel.proyecto.adapters.drivening.http.Handlers.impl;

import com.manuel.proyecto.adapters.drivening.http.Handlers.IAlimentoHandler;
import com.manuel.proyecto.domain.api.IAlimentoServicePort;
import com.manuel.proyecto.domain.model.Alimento;
import com.manuel.proyecto.domain.model.DiasRecomendados;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlimentoHandlerImpl implements IAlimentoHandler {

    private final IAlimentoServicePort aliemntoServicePort;
    @Override
    public List<DiasRecomendados> obtenerAlimento(List<String> nombres, int idUsuario, int mes, int dia) {
        return aliemntoServicePort.obtenerInformacion(nombres,idUsuario,mes,dia);
    }

    @Override
    public List<Alimento> obtenerAlimentos() {
        return aliemntoServicePort.obtenerAlimentos();
    }
}
