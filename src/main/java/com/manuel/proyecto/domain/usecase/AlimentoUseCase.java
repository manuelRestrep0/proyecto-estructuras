package com.manuel.proyecto.domain.usecase;

import com.manuel.proyecto.domain.api.IAlimentoServicePort;
import com.manuel.proyecto.domain.model.Alimento;
import com.manuel.proyecto.domain.model.Dia;
import com.manuel.proyecto.domain.model.DiasRecomendados;
import com.manuel.proyecto.domain.model.Usuario;
import com.manuel.proyecto.domain.spi.AlimentoPersistencePort;
import com.manuel.proyecto.domain.spi.DiaPersistencePort;
import com.manuel.proyecto.domain.utilidades.Limites;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class AlimentoUseCase implements IAlimentoServicePort {

    private final AlimentoPersistencePort alimentoPersistencePort;
    private final DiaPersistencePort diaPersistencePort;

    @Override
    public List<DiasRecomendados> obtenerInformacion(List<String> nombres, int idUsuario, int mes, int dia) {
        for (String nombre:
             nombres) {
            //generar una lista de alimento-usuarios y guardarla completa, no entidad por entidad.
            Alimento alimento = alimentoPersistencePort.obtenerInformacion(nombre);
            //guardarAlimentoUsuario(nombre, idUsuario, mes,dia);
            Dia diaActualizado = new Dia();
            diaActualizado.setTotal_proteinas(alimento.getProteina());
            diaActualizado.setTotal_grasas(alimento.getGrasas());
            diaActualizado.setTotal_aguas(alimento.getAguas());
            diaActualizado.setTotal_carbos(alimento.getCarbos());
            alimentoPersistencePort.guardarTotalDia(idUsuario,mes,dia,diaActualizado);
        }
        List<DiasRecomendados> diasRecomendados = diaPersistencePort.obtenerDiasRecomendados(idUsuario, mes);
        DiasRecomendados diaRecomendado = diasRecomendados.stream().filter(diaR -> diaR.getDia()==dia).findFirst().get();
        Usuario usuario = new Usuario();
        usuario.setId(idUsuario);
        usuario.setPeso(60);
        Dia diaInput = alimentoPersistencePort.obtenerTotalDiario(idUsuario,mes,dia);
        diasRecomendados = calcularRecomendadoAguas(diaRecomendado,diaInput,diasRecomendados,usuario);
        diasRecomendados = calcularRecomendadoProteinas(diaRecomendado,diaInput,diasRecomendados,usuario);
        diasRecomendados = calcularRecomendadoGrasas(diaRecomendado,diaInput,diasRecomendados,usuario);
        diasRecomendados = calcularRecomendadoCarbos(diaRecomendado,diaInput,diasRecomendados,usuario);
        diaPersistencePort.guardarDiasRecomendados(diasRecomendados);
        return diasRecomendados;
    }

    @Override
    public List<Alimento> obtenerAlimentos() {
        return alimentoPersistencePort.obtenerAlimentos();
    }

    public void guardarAlimentoUsuario(String nombre, int idUsuario, int mes, int dia){
        alimentoPersistencePort.guardarAlimentoUsuario(nombre,idUsuario,mes,dia);
    }

    public List<DiasRecomendados> calcularRecomendadoAguas(DiasRecomendados diaRecomendado, Dia diaInput,
                                                           List<DiasRecomendados> diasRecomendados,
                                                           Usuario usuario) {
        float aguasRecomendado = diaRecomendado.getAguas();
        double diferencia = aguasRecomendado - diaInput.getTotal_aguas();
        List<DiasRecomendados> nuevoRecomendado = convertirAMutable(diasRecomendados);
        if (diferencia > 0) {
            nuevoRecomendado = calcularDiferenciaSuperior(diaInput,diaRecomendado,diasRecomendados,
                    Limites.limiteSuperiorAgua(usuario.getPeso()),"aguas");
        }
        if (diferencia < 0) {
            nuevoRecomendado = CalcularDiferenciaInferior(diaInput,diaRecomendado,
                    diasRecomendados,Limites.limiteInferiorAgua(usuario.getPeso()),
                    "aguas");
        }
        return nuevoRecomendado;
    }
    public List<DiasRecomendados> calcularRecomendadoGrasas(DiasRecomendados diaRecomendado,Dia diaInput,
                                                            List<DiasRecomendados> diasRecomendados,
                                                            Usuario usuario){
        float grasasRecomendado = diaRecomendado.getGrasas();
        double diferencia = grasasRecomendado - diaInput.getTotal_grasas();
        List<DiasRecomendados> nuevoRecomendado = convertirAMutable(diasRecomendados);
        if (diferencia > 0) {
            nuevoRecomendado = calcularDiferenciaSuperior(diaInput,diaRecomendado,diasRecomendados,
                    Limites.limiteSuperiorGrasas(),"grasas");
        }
        if (diferencia < 0) {
            nuevoRecomendado = CalcularDiferenciaInferior(diaInput,diaRecomendado,diasRecomendados,
                    Limites.limiteInferiorGrasas(),"grasas");
        }
        return nuevoRecomendado;
    }
    public List<DiasRecomendados> calcularRecomendadoProteinas(DiasRecomendados diaRecomendado,Dia diaInput,
                                                               List<DiasRecomendados> diasRecomendados,
                                                               Usuario usuario){
        float ProteinasRecomendado = diaRecomendado.getProteinas();
        double diferencia = ProteinasRecomendado - diaInput.getTotal_proteinas();
        List<DiasRecomendados> nuevoRecomendado = convertirAMutable(diasRecomendados);
        if (diferencia > 0) {
            nuevoRecomendado = calcularDiferenciaSuperior(diaInput,diaRecomendado,diasRecomendados,
                    Limites.limiteSuperiorProteina(usuario.getPeso()),"proteinas");
        }
        if (diferencia < 0) {
            nuevoRecomendado = CalcularDiferenciaInferior(diaInput,diaRecomendado,diasRecomendados,
                    Limites.limiteInferiorProteina(usuario.getPeso()),"proteinas");
        }
        return nuevoRecomendado;
    }
    public List<DiasRecomendados> calcularRecomendadoCarbos(DiasRecomendados diaRecomendado,Dia diaInput,
                                                            List<DiasRecomendados> diasRecomendados,
                                                            Usuario usuario){
        float CarbosRecomendado = diaRecomendado.getCarbos();
        double diferencia = CarbosRecomendado - diaInput.getTotal_carbos();
        List<DiasRecomendados> nuevoRecomendado = convertirAMutable(diasRecomendados);
        if (diferencia > 0) {
            nuevoRecomendado = calcularDiferenciaSuperior(diaInput,diaRecomendado,diasRecomendados,
                    Limites.limiteSuperiorCarbos(),"carbos");
        }
        if (diferencia < 0) {
            nuevoRecomendado = CalcularDiferenciaInferior(diaInput,diaRecomendado,diasRecomendados,
                    Limites.limiteInferiorCarbos(),"carbos");
        }
        return nuevoRecomendado;
    }
    private List<DiasRecomendados> calcularDiferenciaSuperior(Dia input,DiasRecomendados recomendado,
                                                              List<DiasRecomendados> diasRecomendados,
                                                              float limiteSuperior,
                                                              String variable){
        DiasRecomendados dia = recomendado;
        List<DiasRecomendados> nuevosDiasRecomendados = convertirAMutable(diasRecomendados);
        float diferencia = obtenerVariableDiaRecomendado(recomendado,variable) - obtenerVariableDia(input,variable);
        int diaRecomendado = diasRecomendados.indexOf(recomendado);
        while(diferencia!=0){
            if(limiteSuperior == obtenerVariableDiaRecomendado(dia,variable)){
                if(diaRecomendado>=diasRecomendados.size()-1){
                    System.out.println("no cumple");
                    break;
                }
                dia = nuevosDiasRecomendados.get(diaRecomendado+1);
                diaRecomendado++;
                continue;
            }
            if(diferencia > (limiteSuperior - obtenerVariableDiaRecomendado(dia,variable))){
                if(diaRecomendado>=diasRecomendados.size()-1){
                    System.out.println("no cumple");
                    break;
                }
                if(input.getDia()==recomendado.getDia()){
                    DiasRecomendados siguienteDia = nuevosDiasRecomendados.get((diaRecomendado+1));
                    diferencia = diferencia - (limiteSuperior - obtenerVariableDiaRecomendado(dia,variable));
                    siguienteDia = setearVariable(siguienteDia,variable,limiteSuperior);
                    nuevosDiasRecomendados.set(diaRecomendado+1,siguienteDia);
                    dia = siguienteDia;
                    diaRecomendado+=1;
                }
                else{
                    diferencia = diferencia - (limiteSuperior - obtenerVariableDiaRecomendado(dia,variable));
                    dia = setearVariable(dia, variable, limiteSuperior);
                    nuevosDiasRecomendados.set(diaRecomendado,dia);
                    DiasRecomendados siguienteDia = nuevosDiasRecomendados.get((diaRecomendado+1));
                    diaRecomendado++;
                    dia = siguienteDia;
                }
            } else{
                //DiasRecomendados siguienteDia = nuevosDiasRecomendados.get(diaRecomendado + 1);
                float valorVariableDiaActual = obtenerVariableDiaRecomendado(dia,variable);
                dia = setearVariable(dia,variable,(valorVariableDiaActual+diferencia));
                nuevosDiasRecomendados.set(diaRecomendado, dia);
                diferencia = 0;
            }
        }
        return nuevosDiasRecomendados;

    }
    private List<DiasRecomendados> CalcularDiferenciaInferior(Dia input, DiasRecomendados recomendado,
                                                              List<DiasRecomendados> diasRecomendados, float limiteInferior,
                                                              String variable
    ){
        DiasRecomendados dia = recomendado;
        List<DiasRecomendados> nuevosDiasRecomendados = convertirAMutable(diasRecomendados);
        float diferencia = obtenerVariableDiaRecomendado(recomendado,variable) - obtenerVariableDia(input,variable);
        int diaRecomendado = diasRecomendados.indexOf(recomendado);
        while(diferencia!=0){
            if(limiteInferior == obtenerVariableDiaRecomendado(dia,variable)){
                if(diaRecomendado>=diasRecomendados.size()-1){
                    System.out.println("no cumple");
                    break;
                }
                dia = nuevosDiasRecomendados.get(diaRecomendado+1);
                diaRecomendado++;
                continue;
            }
            if(diferencia < (limiteInferior - obtenerVariableDiaRecomendado(dia,variable))){
                if(diaRecomendado>=diasRecomendados.size()-1){
                    System.out.println("no cumple");
                    break;
                }
                if(input.getDia()==dia.getDia()){
                    DiasRecomendados siguienteDia = nuevosDiasRecomendados.get((diaRecomendado+1));
                    diferencia = diferencia - (limiteInferior - obtenerVariableDiaRecomendado(dia,variable));
                    siguienteDia = setearVariable(siguienteDia,variable,limiteInferior);
                    nuevosDiasRecomendados.set(diaRecomendado+1,siguienteDia);
                    dia = siguienteDia;
                    diaRecomendado+=1;
                } else{
                    diferencia = diferencia - (limiteInferior - obtenerVariableDiaRecomendado(dia,variable));
                    dia = setearVariable(dia,variable,limiteInferior);
                    nuevosDiasRecomendados.set(diaRecomendado,dia);
                    DiasRecomendados siguienteDia = nuevosDiasRecomendados.get((diaRecomendado+1));
                    dia = siguienteDia;
                    diaRecomendado+=1;
                }
            } else{
                //DiasRecomendados siguienteDia = diasRecomendados.get(diaRecomendado + 1);
                float valorVariableDiaActual = obtenerVariableDiaRecomendado(dia,variable);
                dia = setearVariable(dia,variable,(valorVariableDiaActual+diferencia));
                nuevosDiasRecomendados.set(diaRecomendado, dia);
                diferencia = 0;
            }
        }
        return nuevosDiasRecomendados;
    }
    private float obtenerVariableDiaRecomendado(DiasRecomendados dia, String variable){
        switch(variable){
            case "aguas":{
                return dia.getAguas();
            }
            case "proteinas":{
                return dia.getProteinas();
            }
            case "carbos":{
                return dia.getCarbos();
            }
            case "grasas":{
                return dia.getGrasas();
            }
            default: return 0;
        }
    }
    private float obtenerVariableDia(Dia dia, String variable){
        switch(variable){
            case "aguas":{
                return dia.getTotal_aguas();
            }
            case "proteinas":{
                return dia.getTotal_proteinas();
            }
            case "carbos":{
                return dia.getTotal_carbos();
            }
            case "grasas":{
                return dia.getTotal_grasas();
            }
            default: return 0;
        }
    }
    private DiasRecomendados setearVariable(DiasRecomendados dia, String variable, float valor){
        DiasRecomendados nuevoDia = dia;
        switch(variable){
            case "aguas":{
                nuevoDia.setAguas(valor);
                return nuevoDia;
            }
            case "proteinas":{
                nuevoDia.setProteinas(valor);
                return nuevoDia;
            }
            case "carbos":{
                nuevoDia.setCarbos(valor);
                return nuevoDia;
            }
            case "grasas":{
                nuevoDia.setGrasas(valor);
                return nuevoDia;
            }
            default: return dia;
        }
    }

    private List<DiasRecomendados> convertirAMutable(List<DiasRecomendados> diasRecomendados){
        List<DiasRecomendados> listaMutable = new ArrayList<>();
        listaMutable.addAll(diasRecomendados);
        return listaMutable;
    }
}
