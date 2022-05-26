package com.business;

import com.helper.Meat;

public class BarbecueBUSINESS {
    
    public static String checkCurrentTemp(Meat meat,Double temp){
        String status = "Não pronto";
        String type = meat.getType();
        String point = meat.getPoint();
        if(temp < 100){
            if(type != "Outro"){
                switch (point) {
                    case "Mal Passada":
                        if(temp >= 48 && temp < 52) status = "Pronto";
                        else if(temp < 48) status = "Esquentando";
                        else status = "Passou do ponto";
                        break;
                    case "Ao ponto para Mal Passada":
                        if(temp >= 52 && temp < 57) status = "Pronto";
                        else if(temp >= 48 && temp < 52) status = "Mal Passada";
                        else if(temp < 48) status = "Esquentando";
                        else status = "Passou do ponto";
                        break;
                    case "Ao ponto":
                        if(temp >= 57 && temp < 62) status = "Pronto";
                        else if(temp >= 48 && temp < 52) status = "Mal Passada";
                        else if(temp >= 52 && temp < 57) status = "Ao ponto para Mal Passada";
                        else if(temp < 48) status = "Esquentando";
                        else status = "Passou do ponto";
                        break;
                    case "Ao ponto para Bem Passada":
                        if(temp >= 62 && temp < 68) status = "Pronto";
                        else if(temp >= 48 && temp < 52) status = "Mal Passada";
                        else if(temp >= 52 && temp < 57) status = "Ao ponto para Mal Passada";
                        else if(temp >= 57 && temp < 62) status = "Ao ponto";
                        else if(temp < 48) status = "Esquentando";
                        else status = "Passou do ponto";
                        break;
                    case "Bem Passada":
                        if(temp >= 68 && temp < 75) status = "Pronto";
                        else if(temp >= 48 && temp < 52) status = "Mal Passada";
                        else if(temp >= 52 && temp < 57) status = "Ao ponto para Mal Passada";
                        else if(temp >= 57 && temp < 62) status = "Ao ponto";
                        else if(temp >= 62 && temp < 68) status = "Ao ponto para Bem Passada";
                        else if(temp < 48) status = "Esquentando";
                        else status = "Passou do ponto";
                        break;
                }
            }
            else{
                if(temp >= meat.getTemp_min() && temp < meat.getTemp_max()) status = "Pronto";
                else if(temp < meat.getTemp_min()) status = "Esquentando";
                else status = "Passou do ponto";
            }
        }
        else{
            status = "Carne Carvão";
        }
        return status;
    }


}