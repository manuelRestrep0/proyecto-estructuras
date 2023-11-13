package com.manuel.proyecto.domain.usecase;

import com.manuel.proyecto.domain.model.Dia;
import com.manuel.proyecto.domain.model.Usuario;
import com.manuel.proyecto.domain.utilidades.Limites;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class DiaUseCase {

    private List<Dia> diasRecomendados;
    private Dia diaInput;
    private Usuario usuario;

    /*public void calcularNuevoRecomendado(){
        Optional<Dia> diaRecomendado = diasRecomendados.stream().filter(dia -> dia.getDia()==diaInput.getDia()).findFirst();
        if(diaRecomendado.isPresent()){
            //ejecutar funciones.
        }else{
            //se acabo el mes.
        }
    }*/
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
        //TODO: recalcular el recomendado
        if (diferencia > 0) {
        }
        if (diferencia < 0) {
        }
    }
    public void calcularRecomendadoProteinas(Dia diaRecomendado){
        float ProteinasRecomendado = diaRecomendado.getProteinas();
        double diferencia = ProteinasRecomendado - diaInput.getProteinas();
        System.out.println(diferencia);
        //TODO: recalcular el recomendado
        if (diferencia > 0) {
            //diferencia = calcularDiferenciaSuperiorAguas(diaInput, diaRecomendado);
        }
        if (diferencia < 0) {
            //diferencia = calcularDiferenciaInferiorAguas(diaInput, diaRecomendado);
        }
    }
    public void calcularRecomendadoCarbos(Dia diaRecomendado){
        float CarbosRecomendado = diaRecomendado.getCarbos();
        double diferencia = CarbosRecomendado - diaInput.getCarbos();
        System.out.println(diferencia);
        //TODO: recalcular el recomendado
        if (diferencia > 0) {
            //diferencia = calcularDiferenciaSuperiorAguas(diaInput, diaRecomendado);
        }
        if (diferencia < 0) {
            //diferencia = calcularDiferenciaInferiorAguas(diaInput, diaRecomendado);
        }
    }
    private float calcularDiferenciaSuperiorAguas(Dia input, Dia recomendado) {
        Dia dia = recomendado;
        float diferencia = dia.getAguas() - input.getAguas();
        float aguas = dia.getAguas();
        int diaRecomendado = diasRecomendados.indexOf(dia);
        while (diferencia != 0) {
            //TODO: condicional para el dia final.
            if(Limites.limiteSuperiorAgua(usuario.getPeso()) == dia.getAguas()){
                if(diaRecomendado>=diasRecomendados.size()-1){
                    System.out.println("no cumpliste");
                    break;
                }
                dia = diasRecomendados.get(diaRecomendado + 1);
                continue;
            }
            if (diferencia > (Limites.limiteSuperiorAgua(usuario.getPeso()) - dia.getAguas())) {
                if(diaRecomendado>=diasRecomendados.size()-1){
                    System.out.println("no cumpliste");
                    break;
                }
                Dia siguienteDia = diasRecomendados.get(diaRecomendado + 1);
                diferencia = diferencia - (Limites.limiteSuperiorAgua(usuario.getPeso()) - dia.getAguas());
                siguienteDia.setAguas(Limites.limiteSuperiorAgua(usuario.getPeso()));
                diasRecomendados.set(diaRecomendado + 1, siguienteDia);
                dia = siguienteDia;
                diaRecomendado += 1;
            } else {
                Dia siguienteDia = diasRecomendados.get(diaRecomendado + 1);
                siguienteDia.setAguas(dia.getAguas() + diferencia);
                diasRecomendados.set(diaRecomendado + 1, siguienteDia);
                diferencia = 0;
            }
        }
        return diferencia;
    }
    private float calcularDiferenciaInferiorAguas(Dia input, Dia recomendado) {
        Dia dia = recomendado;
        float diferencia = recomendado.getAguas() - input.getAguas();
        int diaRecomendado = diasRecomendados.indexOf(recomendado);
        while (diferencia != 0) {
            if(Limites.limiteInferiorAgua(usuario.getPeso()) == dia.getAguas()){
                dia = diasRecomendados.get(diaRecomendado + 1);
                continue;
            }
            if (diferencia < (Limites.limiteInferiorAgua(usuario.getPeso()) - dia.getAguas())) {
                Dia siguienteDia = diasRecomendados.get(diaRecomendado + 1);
                diferencia = diferencia - (Limites.limiteInferiorAgua(usuario.getPeso()) - dia.getAguas());
                siguienteDia.setAguas(Limites.limiteInferiorAgua(usuario.getPeso()));
                diasRecomendados.set(diaRecomendado + 1, siguienteDia);
                dia = siguienteDia;
                diaRecomendado += 1;
            } else {
                Dia siguienteDia = diasRecomendados.get(diaRecomendado + 1);
                siguienteDia.setAguas(dia.getAguas() + diferencia);
                diasRecomendados.set(diaRecomendado + 1, siguienteDia);
                diferencia = 0;
            }
        }
        return diferencia;
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
    public static void main(String[] args){
        Usuario user = new Usuario();
        user.setPeso(60);
        Dia input = new Dia();
        input.setDia(3);
        input.setAguas(1405);
        Dia recomendado1 = new Dia();
        recomendado1.setDia(1);
        recomendado1.setAguas(2100);
        Dia recomendado2 = new Dia();
        recomendado2.setDia(2);
        recomendado2.setAguas(2100);
        Dia recomendado3 = new Dia();
        recomendado3.setDia(3);
        recomendado3.setAguas(2100);
        Dia recomendado4 = new Dia();
        recomendado4.setDia(4);
        recomendado4.setAguas(2100);
        Dia recomendado5 = new Dia();
        recomendado5.setDia(5);
        recomendado5.setAguas(2100);
        Dia recomendado6 = new Dia();
        recomendado6.setDia(6);
        recomendado6.setAguas(2100);

        List<Dia> dias = new ArrayList<>();
        dias.add(recomendado1);
        dias.add(recomendado2);
        dias.add(recomendado3);
        dias.add(recomendado4);
        dias.add(recomendado5);
        dias.add(recomendado6);


        DiaUseCase caso = new DiaUseCase();
        caso.setDiasRecomendados(dias);
        caso.setDiaInput(input);
        caso.setUsuario(user);
        caso.calcularRecomendadoAguas(recomendado1);
        input.setAguas(2700);
        caso.setDiaInput(input);
        caso.calcularRecomendadoAguas(recomendado2);
        input.setAguas(2800);
        caso.setDiaInput(input);
        caso.calcularRecomendadoAguas(recomendado3);

        System.out.println(dias.size());
        System.out.print("dia 1: ");
        System.out.println(caso.diasRecomendados.get(0).getAguas());
        System.out.print("dia 2: ");
        System.out.println(caso.diasRecomendados.get(1).getAguas());
        System.out.print("dia 3: ");
        System.out.println(caso.diasRecomendados.get(2).getAguas());
        System.out.println(caso.diasRecomendados.get(3).getAguas());
        System.out.println(caso.diasRecomendados.get(4).getAguas());
        System.out.println(caso.diasRecomendados.get(5).getAguas());


    }
}
