package com.manuel.proyecto.domain.utilidades;

public class Recomendado {

    private float peso;
    private float caloriasDiarias = 2000;
    private final int agua = 35;
    private final float proteina = 1.5F;
    public double obtenerAguaRecomendada(){
        return agua*peso;
    }
    public double obtenerProteinasRecomendada(){
        return proteina*peso;
    }
    public double obtenerGrasasRecomendadas(){
        return 0.30*(caloriasDiarias/9);
    }
    public double obtenerCarbosRecomendados(){
        return 0.50*(caloriasDiarias/4);
    }
}
