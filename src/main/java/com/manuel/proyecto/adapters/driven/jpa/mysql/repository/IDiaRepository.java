package com.manuel.proyecto.adapters.driven.jpa.mysql.repository;

import com.manuel.proyecto.adapters.driven.jpa.mysql.entity.DiaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDiaRepository extends JpaRepository<DiaEntity,Integer> {
}
