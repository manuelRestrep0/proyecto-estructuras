package com.manuel.proyecto.adapters.driven.jpa.mysql.adapter;

import com.manuel.proyecto.adapters.driven.jpa.mysql.mapper.DiaEntityMapper;
import com.manuel.proyecto.adapters.driven.jpa.mysql.repository.IDiaRepository;
import com.manuel.proyecto.domain.model.Dia;
import com.manuel.proyecto.domain.spi.DiaPersistencePort;

import java.util.List;

public class DiaMysqlAdapter implements DiaPersistencePort {
    IDiaRepository diaRepository;
    DiaEntityMapper diaEntityMapper;
    @Override
    public List<Dia> obtenerDiasRecomendados(int idUsuario) {
        return diaRepository.findAllByIdUsuarioAndEsRecomendado(idUsuario,true).stream().map(diaEntityMapper::toDia).toList();
    }

    @Override
    public void guardarDiasRecomendados(List<Dia> dias) {
        diaRepository.saveAll(dias.stream().map(diaEntityMapper::toEntity).toList());
    }
}
