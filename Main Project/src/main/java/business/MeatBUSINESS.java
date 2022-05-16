package business;

import java.util.ArrayList;


import dao.MeatDAO;
import helper.Meat;

public class MeatBUSINESS {
    
    public static ArrayList<Double> checkTemp(String point){
        Double min_temp = 0.0;
        Double max_temp = 0.0;
        switch (point) {
            case "Mal Passada":
                min_temp = 30.0;
                max_temp = 33.0;
                break;
            case "Ao ponto para Mal Passada":
                min_temp = 33.0;
                max_temp = 36.0;
                break;
            case "Ao ponto":
                min_temp = 36.0;
                max_temp = 39.0;
                break;
            case "Ao ponto para Bem Passada":
                min_temp = 39.0;
                max_temp = 42.0;
                break;
            case "Bem Passada":
                min_temp = 42.0;
                max_temp = 45.0;
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