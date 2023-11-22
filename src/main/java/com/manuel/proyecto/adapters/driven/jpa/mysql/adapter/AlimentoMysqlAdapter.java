package com.manuel.proyecto.adapters.driven.jpa.mysql.adapter;

import com.manuel.proyecto.adapters.driven.jpa.mysql.entity.TotalDiarioUsuarioEntity;
import com.manuel.proyecto.adapters.driven.jpa.mysql.entity.UsuariosAlimentosEntity;
import com.manuel.proyecto.adapters.driven.jpa.mysql.mapper.AlimentoEntityMapper;
import com.manuel.proyecto.adapters.driven.jpa.mysql.mapper.TotalDiarioUsuarioMapper;
import com.manuel.proyecto.adapters.driven.jpa.mysql.repository.IAlimentoRepository;
import com.manuel.proyecto.adapters.driven.jpa.mysql.repository.TotalDiarioUsuarioRepository;
import com.manuel.proyecto.adapters.driven.jpa.mysql.repository.UsuariosAlimentosRepository;
import com.manuel.proyecto.domain.model.Alimento;
import com.manuel.proyecto.domain.model.Dia;
import com.manuel.proyecto.domain.spi.AlimentoPersistencePort;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class AlimentoMysqlAdapter implements AlimentoPersistencePort {

    private final IAlimentoRepository alimentoRepository;
    private final AlimentoEntityMapper alimentoEntityMapper;
    private final UsuariosAlimentosRepository usuariosAlimentosRepository;
    private final TotalDiarioUsuarioRepository totalDiarioUsuarioRepository;
    private final TotalDiarioUsuarioMapper totalDiarioUsuarioMapper;

    @Override
    public Alimento obtenerInformacion(String nombre) {
        return alimentoEntityMapper.toAlimento(alimentoRepository.findByNombre(nombre));
    }

    @Override
    public void guardarAlimentoUsuario(String nombre, int idUsuario, int mes, int dia) {
        UsuariosAlimentosEntity usuariosAlimentosEntity = new UsuariosAlimentosEntity();
        usuariosAlimentosEntity.setAlimento(alimentoRepository.findByNombre(nombre).getId());
        usuariosAlimentosEntity.setUsuario(idUsuario);
        usuariosAlimentosEntity.setMes(mes);
        usuariosAlimentosEntity.setDia(dia);
        usuariosAlimentosRepository.save(usuariosAlimentosEntity);
    }
    @Override
    public Dia obtenerTotalDiario(int idUsuario, int mes, int dia) {
        return totalDiarioUsuarioMapper.toDia(totalDiarioUsuarioRepository.findByUsuarioAndMesAndDia(idUsuario, mes, dia));
    }
    @Override
    public void guardarTotalDia(int idUsuario, int mes, int dia, Dia diaActualizado) {
        TotalDiarioUsuarioEntity totalDiarioUsuario = totalDiarioUsuarioRepository.findByUsuarioAndMesAndDia(idUsuario, mes, dia);
        System.out.println(totalDiarioUsuario.toString());
        totalDiarioUsuario.setTotal_aguas(totalDiarioUsuario.getTotal_aguas()+diaActualizado.getTotal_aguas());
        totalDiarioUsuario.setTotal_carbos(totalDiarioUsuario.getTotal_carbos()+diaActualizado.getTotal_carbos());
        totalDiarioUsuario.setTotal_grasas(totalDiarioUsuario.getTotal_grasas()+ diaActualizado.getTotal_grasas());
        totalDiarioUsuario.setTotal_proteinas(totalDiarioUsuario.getTotal_proteinas()+diaActualizado.getTotal_proteinas());
        totalDiarioUsuarioRepository.save(totalDiarioUsuario);
    }
}
