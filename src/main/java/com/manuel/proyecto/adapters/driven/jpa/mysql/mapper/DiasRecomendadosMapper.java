package com.manuel.proyecto.adapters.driven.jpa.mysql.mapper;

import com.manuel.proyecto.adapters.driven.jpa.mysql.entity.DiasRecomendadosEntity;
import com.manuel.proyecto.domain.model.DiasRecomendados;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface DiasRecomendadosMapper {

    DiasRecomendadosEntity toEntity(DiasRecomendados diasRecomendados);
    DiasRecomendados toDiasRecomendados(DiasRecomendadosEntity diasRecomendadosEntity);
}
