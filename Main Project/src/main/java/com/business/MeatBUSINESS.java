package com.business;

import java.util.ArrayList;

import com.dao.MeatDAO;
import com.helper.Meat;

public class MeatBUSINESS {
    
    public static ArrayList<Double> checkTemp(String point){
        Double min_temp = 0.0;
        Double max_temp = 0.0;
        switch (point) {
            case "Mal Passada":
                min_temp = 48.0;
                max_temp = 52.0;
                break;
            case "Ao ponto para Mal Passada":
                min_temp = 53.0;
                max_temp = 57.0;
                break;
            case "Ao ponto":
                min_temp = 58.0;
                max_temp = 62.0;
                break;
            case "Ao ponto para Bem Passada":
                min_temp = 63.0;
                max_temp = 68.0;
                break;
            case "Bem Passada":
                min_temp = 69.0;
                max_temp = 75.0;
                break;
        }
        ArrayList<Double> temps = new ArrayList<Double>();
        temps.add(min_temp);
        temps.add(max_temp);
        return temps;
    }

    public static Meat checkMeat(String name) throws Exception{

        Meat meat = MeatDAO.getInstance().getMeat(name);

        if(meat == null) return null;
        return meat;
    }
}