package com.manuel.proyecto.adapters.driven.jpa.mysql.mapper;

import com.manuel.proyecto.domain.model.Dia;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface DiaEntityMapper {
}
