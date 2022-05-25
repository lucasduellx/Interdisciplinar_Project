package com.business;

public class BarbecueBUSINESS {
    
    public static String checkCurrentTemp(String point,Double temp){
        String status = "Não pronto";
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
        return status;
    }


}