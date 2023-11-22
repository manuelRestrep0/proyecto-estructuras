package com.manuel.proyecto.adapters.driven.jpa.mysql.repository;

import com.manuel.proyecto.adapters.driven.jpa.mysql.entity.DiasRecomendadosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IDiasRecomendadosRepository extends JpaRepository<DiasRecomendadosEntity, Integer> {

    List<DiasRecomendadosEntity> findAllByUsuarioAndMes(int idUsuario, int mes);
}
