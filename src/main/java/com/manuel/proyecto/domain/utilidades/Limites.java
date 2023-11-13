package com.manuel.proyecto.domain.utilidades;

public class Limites {
    private static  final int agua = 35;
    private static final float proteina = 1.5F;
    private static final int caloriasDiarias = 2000;

    public static float limiteInferiorAgua(float peso){
        return agua*(peso-10);
    }
    public static float limiteSuperiorAgua(float peso){
        return agua*(peso+10);
    }
    public static float limiteInferiorProteina(float peso){
        return proteina*(peso-10);
    }
    public static float limiteSuperiorProteina(float peso){
        return proteina*(peso+10);
    }
    public static float limiteInferiorGrasas(){
        return (float) (0.15*(caloriasDiarias/9));
    }
    public static float limiteSuperiorGrasas(){
        return (float) (0.45*(caloriasDiarias/9));
    }
    public static float limiteInferiorCarbos(){
        return (float) (0.40*(caloriasDiarias/4));
    }
    public static float limiteSuperiorCarbos(){
        return (float) (0.60*(caloriasDiarias/4));
    }
}
