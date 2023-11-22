package com.manuel.proyecto.domain.usecase;

import com.manuel.proyecto.domain.api.IDiaServicePort;
import com.manuel.proyecto.domain.model.Dia;
import com.manuel.proyecto.domain.model.DiasRecomendados;
import com.manuel.proyecto.domain.model.Usuario;
import com.manuel.proyecto.domain.spi.DiaPersistencePort;
import com.manuel.proyecto.domain.utilidades.Limites;
import com.manuel.proyecto.domain.utilidades.Recomendado;
import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Getter
@Setter
public class DiaUseCase implements IDiaServicePort {
    private final DiaPersistencePort diaPersistencePort;
    @Override
    public void generarDiasRecomendados(int idUsuario, float peso, int mes) {
        List<DiasRecomendados> dias = new ArrayList<>();
        for(int i=1;i<=30;i++){
            DiasRecomendados dia = new DiasRecomendados();
            dia.setUsuario(idUsuario);
            dia.setAguas((float) Recomendado.obtenerAguaRecomendada(peso));
            dia.setCarbos((float)Recomendado.obtenerCarbosRecomendados());
            dia.setGrasas((float)Recomendado.obtenerGrasasRecomendadas());
            dia.setProteinas((float)Recomendado.obtenerProteinasRecomendada(peso));
            dia.setMes(mes);
            dia.setDia(i);
            dias.add(dia);
        }
        guardarDiasRecomendados(dias);
    }

    @Override
    public void generarDiasMes(int idUsuario, int mes) {
        List<Dia> dias = new ArrayList<>();
        for(int i=1;i<=30;i++){
            Dia dia = new Dia();
            dia.setUsuario(idUsuario);
            dia.setTotal_aguas(0);
            dia.setTotal_grasas(0);
            dia.setTotal_carbos(0);
            dia.setTotal_proteinas(0);
            dia.setDia(i);
            dia.setMes(mes);
            dias.add(dia);
        }
        diaPersistencePort.guardarTotalDias(dias);

    }
    @Override
    public List<DiasRecomendados> obtenerDiasRecomendados(int idUsuario, int mes) {
        return diaPersistencePort.obtenerDiasRecomendados(idUsuario,mes);
    }
    private void guardarDiasRecomendados(List<DiasRecomendados> diasRecomendados){
        diaPersistencePort.guardarDiasRecomendados(diasRecomendados);
    }
}
