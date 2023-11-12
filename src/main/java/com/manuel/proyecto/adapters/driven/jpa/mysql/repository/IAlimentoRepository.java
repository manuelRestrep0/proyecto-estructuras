package com.manuel.proyecto.adapters.driven.jpa.mysql.repository;

import com.manuel.proyecto.adapters.driven.jpa.mysql.entity.AlimentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAlimentoRepository extends JpaRepository<AlimentoEntity,Integer> {
}
