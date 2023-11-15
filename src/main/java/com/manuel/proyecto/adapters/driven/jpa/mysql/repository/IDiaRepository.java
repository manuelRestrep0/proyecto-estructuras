package com.manuel.proyecto.adapters.driven.jpa.mysql.repository;

import com.manuel.proyecto.adapters.driven.jpa.mysql.entity.DiaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IDiaRepository extends JpaRepository<DiaEntity,Integer> {

    List<DiaEntity> findDiaEntitiesByIdUsuarioAndEsRecomendado(int idUsuario, boolean esRecomendado);
    List<DiaEntity> findAllByIdUsuarioAndEsRecomendado(int idUsuario, boolean esRecomendado);

}
