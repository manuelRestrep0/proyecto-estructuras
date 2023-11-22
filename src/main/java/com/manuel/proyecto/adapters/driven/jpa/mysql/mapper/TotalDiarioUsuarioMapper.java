package com.manuel.proyecto.adapters.driven.jpa.mysql.mapper;

import com.manuel.proyecto.adapters.driven.jpa.mysql.entity.TotalDiarioUsuarioEntity;
import com.manuel.proyecto.domain.model.Dia;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface TotalDiarioUsuarioMapper {

    Dia toDia(TotalDiarioUsuarioEntity totalDiarioUsuarioEntity);
    TotalDiarioUsuarioEntity toEntity(Dia dia);
}
