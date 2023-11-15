package com.manuel.proyecto.adapters.drivening.http.Handlers.impl;

import com.manuel.proyecto.adapters.drivening.http.Handlers.IDiaHandler;
import com.manuel.proyecto.domain.api.IDiaServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiaHandlerImpl implements IDiaHandler {

    private final IDiaServicePort diaServicePort;

    @Override
    public void generarDiasRecomendados(int idUsuario, float peso) {
        diaServicePort.generarDiasRecomendados(idUsuario, peso);
    }
}
