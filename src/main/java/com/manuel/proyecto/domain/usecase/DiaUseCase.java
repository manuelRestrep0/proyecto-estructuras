package com.manuel.proyecto.domain.usecase;

import com.manuel.proyecto.domain.api.IDiaServicePort;
import com.manuel.proyecto.domain.model.Dia;
import com.manuel.proyecto.domain.model.Usuario;
import com.manuel.proyecto.domain.spi.DiaPersistencePort;
import com.manuel.proyecto.domain.utilidades.Limites;
import com.manuel.proyecto.domain.utilidades.Recomendado;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class DiaUseCase implements IDiaServicePort {

    private List<Dia> diasRecomendados;
    private Dia diaInput;
    private Usuario usuario;
    private final DiaPersistencePort diaPersistencePort;

    public void calcularNuevoRecomendado(){
        Optional<Dia> diaRecomendado = diasRecomendados.stream().filter(dia -> dia.getDia()==diaInput.getDia()).findFirst();
        if(diaRecomendado.isPresent()){
            calcularRecomendadoAguas(diaRecomendado.get());
            calcularRecomendadoCarbos(diaRecomendado.get());
            calcularRecomendadoGrasas(diaRecomendado.get());
            calcularRecomendadoProteinas(diaRecomendado.get());
        }else{
            System.out.println("Ya no puedes cumplir la meta.");
        }
    }
    public void calcularRecomendadoAguas(Dia diaRecomendado) {
        float aguasRecomendado = diaRecomendado.getAguas();
        double diferencia = aguasRecomendado - diaInput.getAguas();
        if (diferencia > 0) {
            this.diasRecomendados = calcularDiferenciaSuperior(diaInput,diaRecomendado,diasRecomendados,
                    Limites.limiteSuperiorAgua(usuario.getPeso()),"aguas");
        }
        if (diferencia < 0) {
            this.diasRecomendados = CalcularDiferenciaInferior(diaInput,diaRecomendado,
                    diasRecomendados,Limites.limiteInferiorAgua(usuario.getPeso()),
                    "aguas");
        }
    }
    public void calcularRecomendadoGrasas(Dia diaRecomendado){
        float grasasRecomendado = diaRecomendado.getGrasas();
        double diferencia = grasasRecomendado - diaInput.getGrasas();
        if (diferencia > 0) {
            this.diasRecomendados = calcularDiferenciaSuperior(diaInput,diaRecomendado,diasRecomendados,
                    Limites.limiteSuperiorGrasas(),"grasas");
        }
        if (diferencia < 0) {
            this.diasRecomendados = CalcularDiferenciaInferior(diaInput,diaRecomendado,diasRecomendados,
                    Limites.limiteInferiorGrasas(),"grasas");
        }
    }
    public void calcularRecomendadoProteinas(Dia diaRecomendado){
        float ProteinasRecomendado = diaRecomendado.getProteinas();
        double diferencia = ProteinasRecomendado - diaInput.getProteinas();
        if (diferencia > 0) {
            this.diasRecomendados = calcularDiferenciaSuperior(diaInput,diaRecomendado,diasRecomendados,
                    Limites.limiteSuperiorProteina(usuario.getPeso()),"proteinas");
        }
        if (diferencia < 0) {
            this.diasRecomendados = CalcularDiferenciaInferior(diaInput,diaRecomendado,diasRecomendados,
                    Limites.limiteInferiorProteina(usuario.getPeso()),"proteinas");
        }
    }
    public void calcularRecomendadoCarbos(Dia diaRecomendado){
        float CarbosRecomendado = diaRecomendado.getCarbos();
        double diferencia = CarbosRecomendado - diaInput.getCarbos();
        if (diferencia > 0) {
            this.diasRecomendados = calcularDiferenciaSuperior(diaInput,diaRecomendado,diasRecomendados,
                    Limites.limiteSuperiorCarbos(),"carbos");
        }
        if (diferencia < 0) {
            this.diasRecomendados = CalcularDiferenciaInferior(diaInput,diaRecomendado,diasRecomendados,
                    Limites.limiteInferiorCarbos(),"carbos");
        }
    }
    private List<Dia> calcularDiferenciaSuperior(Dia input,Dia recomendado,
                                             List<Dia> diasRecomendados,
                                             float limiteSuperior,
                                             String variable){
        Dia dia = recomendado;
        float diferencia = obtenerVariable(recomendado,variable) - obtenerVariable(input,variable);
        int diaRecomendado = diasRecomendados.indexOf(recomendado);
        while(diferencia!=0){
            System.out.println(diferencia);
            if(limiteSuperior == obtenerVariable(dia,variable)){
                if(diaRecomendado>=diasRecomendados.size()-1){
                    System.out.println("no cumple");
                    break;
                }
                dia = diasRecomendados.get(diaRecomendado+1);
                continue;
            }
            if(diferencia > (limiteSuperior - obtenerVariable(dia,variable))){
                if(diaRecomendado>=diasRecomendados.size()-1){
                    System.out.println("no cumple");
                    break;
                }
                Dia siguienteDia = diasRecomendados.get((diaRecomendado+1));
                diferencia = diferencia - (limiteSuperior - obtenerVariable(dia,variable));
                siguienteDia = setearVariable(siguienteDia,variable,limiteSuperior);
                diasRecomendados.set(diaRecomendado+1,siguienteDia);
                dia = siguienteDia;
                diaRecomendado+=1;
            } else{
                Dia siguienteDia = diasRecomendados.get(diaRecomendado + 1);
                float valorVariableDiaActual = obtenerVariable(dia,variable);
                siguienteDia = setearVariable(siguienteDia,variable,(valorVariableDiaActual+diferencia));
                diasRecomendados.set(diaRecomendado + 1, siguienteDia);
                diferencia = 0;
            }
        }
        return diasRecomendados;

    }
    private List<Dia> CalcularDiferenciaInferior(Dia input, Dia recomendado,
                                             List<Dia> diasRecomendados, float limiteInferior,
                                             String variable
                                             ){
        Dia dia = recomendado;
        float diferencia = obtenerVariable(recomendado,variable) - obtenerVariable(input,variable);
        int diaRecomendado = diasRecomendados.indexOf(recomendado);
        while(diferencia!=0){
            System.out.println(diferencia);
            if(limiteInferior == obtenerVariable(dia,variable)){
                if(diaRecomendado>=diasRecomendados.size()-1){
                    System.out.println("no cumple");
                    break;
                }
                dia = diasRecomendados.get(diaRecomendado+1);
                continue;
            }
            if(diferencia < (limiteInferior - obtenerVariable(dia,variable))){
                if(diaRecomendado>=diasRecomendados.size()-1){
                    System.out.println("no cumple");
                    break;
                }
                Dia siguienteDia = diasRecomendados.get((diaRecomendado+1));
                diferencia = diferencia - (limiteInferior - obtenerVariable(dia,variable));
                siguienteDia = setearVariable(siguienteDia,variable,limiteInferior);
                diasRecomendados.set(diaRecomendado+1,siguienteDia);
                dia = siguienteDia;
                diaRecomendado+=1;
            } else{
                Dia siguienteDia = diasRecomendados.get(diaRecomendado + 1);
                float valorVariableDiaActual = obtenerVariable(dia,variable);
                siguienteDia = setearVariable(siguienteDia,variable,(valorVariableDiaActual+diferencia));
                diasRecomendados.set(diaRecomendado + 1, siguienteDia);
                diferencia = 0;
            }
        }
        return diasRecomendados;
    }
    private float obtenerVariable(Dia dia, String variable){
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
    private Dia setearVariable(Dia dia, String variable, float valor){
        Dia nuevoDia = dia;
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
    @Override
    public void generarDiasRecomendados(int idUsuario, float peso) {
        List<Dia> dias = new ArrayList<>();
        for(int i=1;i<=30;i++){
            Dia dia = new Dia();
            dia.setDia(i);
            dia.setIdUsuario(idUsuario);
            dia.setEsRecomendado(true);
            dia.setAguas((float) Recomendado.obtenerAguaRecomendada(peso));
            dia.setCarbos((float)Recomendado.obtenerCarbosRecomendados());
            dia.setGrasas((float)Recomendado.obtenerGrasasRecomendadas());
            dia.setProteinas((float)Recomendado.obtenerProteinasRecomendada(peso));
        }
        guardarDiasRecomendados(dias);
    }
    private void guardarDiasRecomendados(List<Dia> diasRecomendados){
        diaPersistencePort.guardarDiasRecomendados(diasRecomendados);
    }
    private List<Dia> obtenerDiasRecomendados(int idUsuario){
        return diaPersistencePort.obtenerDiasRecomendados(idUsuario);
    }
}
