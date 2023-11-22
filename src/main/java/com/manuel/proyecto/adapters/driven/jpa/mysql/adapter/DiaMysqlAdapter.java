package com.manuel.proyecto.adapters.driven.jpa.mysql.adapter;

import com.manuel.proyecto.adapters.driven.jpa.mysql.entity.DiasRecomendadosEntity;
import com.manuel.proyecto.adapters.driven.jpa.mysql.mapper.DiasRecomendadosMapper;
import com.manuel.proyecto.adapters.driven.jpa.mysql.mapper.TotalDiarioUsuarioMapper;
import com.manuel.proyecto.adapters.driven.jpa.mysql.repository.IDiasRecomendadosRepository;
import com.manuel.proyecto.adapters.driven.jpa.mysql.repository.TotalDiarioUsuarioRepository;
import com.manuel.proyecto.domain.model.Dia;
import com.manuel.proyecto.domain.model.DiasRecomendados;
import com.manuel.proyecto.domain.spi.DiaPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class DiaMysqlAdapter implements DiaPersistencePort {

    private final IDiasRecomendadosRepository diasRecomendadosRepository;
    private final DiasRecomendadosMapper diasRecomendadosMapper;
    private final TotalDiarioUsuarioRepository totalDiarioUsuarioRepository;
    private final TotalDiarioUsuarioMapper totalDiarioUsuarioMapper;
    @Override
    public List<DiasRecomendados> obtenerDiasRecomendados(int idUsuario, int mes) {
        return diasRecomendadosRepository.findAllByUsuarioAndMes(idUsuario,mes).stream().map(diasRecomendadosMapper::toDiasRecomendados).toList();
    }

    @Override
    public List<Dia> obtenerDiasIngresados(int idUsuario, int mes) {
        return totalDiarioUsuarioRepository.findAllByUsuarioAndMes(idUsuario, mes).stream().map(totalDiarioUsuarioMapper::toDia).toList();
    }

    @Override
    public void guardarDiasRecomendados(List<DiasRecomendados> dias) {
        diasRecomendadosRepository.saveAll(dias.stream().map(diasRecomendadosMapper::toEntity).toList());
    }
    @Override
    public void guardarTotalDias(List<Dia> dias) {
        totalDiarioUsuarioRepository.saveAll(dias.stream().map(totalDiarioUsuarioMapper::toEntity).toList());
    }

}
