package com.manuel.proyecto.domain.utilidades;

public class Recomendado {

    //private static float peso;
    private static float caloriasDiarias = 2000;
    private static final int agua = 35;
    private static final float proteina = 1.5F;
    public static double obtenerAguaRecomendada(float peso){
        return agua*peso;
    }
    public static double obtenerProteinasRecomendada(float peso){
        return proteina*peso;
    }
    public static double obtenerGrasasRecomendadas(){
        return 0.30*(caloriasDiarias/9);
    }
    public static double obtenerCarbosRecomendados(){
        return 0.50*(caloriasDiarias/4);
    }
}
