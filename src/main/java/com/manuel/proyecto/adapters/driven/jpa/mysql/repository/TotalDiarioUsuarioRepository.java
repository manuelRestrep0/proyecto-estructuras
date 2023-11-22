package com.manuel.proyecto.adapters.driven.jpa.mysql.repository;

import com.manuel.proyecto.adapters.driven.jpa.mysql.entity.TotalDiarioUsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TotalDiarioUsuarioRepository extends JpaRepository<TotalDiarioUsuarioEntity, Integer> {

    TotalDiarioUsuarioEntity findByUsuarioAndMesAndDia(int idUsuario, int mes, int dia);
    List<TotalDiarioUsuarioEntity> findAllByUsuarioAndMes(int idUsuario, int mes);
}
