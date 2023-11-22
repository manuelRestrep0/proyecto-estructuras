package com.manuel.proyecto.domain.spi;

import com.manuel.proyecto.domain.model.Alimento;
import com.manuel.proyecto.domain.model.Dia;

public interface AlimentoPersistencePort {

    Alimento obtenerInformacion(String nombre);
    void guardarAlimentoUsuario(String nombre, int idUsuario, int mes, int dia);
    Dia obtenerTotalDiario(int idUsuario, int mes, int dia);
    void guardarTotalDia(int idUsuario, int mes, int dia, Dia diaActualizado);
}
