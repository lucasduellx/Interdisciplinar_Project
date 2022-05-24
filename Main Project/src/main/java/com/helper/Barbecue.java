package com.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Barbecue {
    private Map<Stick,Meat> stick_meat = new HashMap<Stick,Meat>();
    private ArrayList<Barbecue> help = new ArrayList<Barbecue>();
    private Integer pessoas;
    private Meat newMeat;
    private Stick newStick;
    private String newMeatName;
    private String newStickName;
    private Double temp = 0.0;
    private String status = "Não Pronto";

    private static Barbecue bbc;
    
    private Barbecue(){}

    public Barbecue(Stick stick,Meat meat){
        setNewStick(stick);
        setNewMeat(meat);
    }

    public static Barbecue getInstance(){
        if(bbc == null){
            bbc = new Barbecue();
        }
        return bbc;
    }

    public Map<Stick,Meat> getStick_meat() {
        return stick_meat;
    }

    public void setStick_meat(Map<Stick,Meat> stick_meat) {
        this.stick_meat = stick_meat;
    }

    public Integer getPessoas() {
        return pessoas;
    }

    public void setPessoas(Integer pessoas) {
        this.pessoas = pessoas;
    }

    public void addMap(Stick stick,Meat meat){
        this.stick_meat.put(stick, meat);
    }

    public void removeMap(Stick stick){
        this.stick_meat.remove(stick);
    }

    public Meat getNewMeat() {
        return newMeat;
    }

    public void setNewMeat(Meat newMeat) {
        this.newMeat = newMeat;
        this.newMeatName = newMeat.getName();
    }

    public Stick getNewStick() {
        return newStick;
    }

    public void setNewStick(Stick newStick) {
        this.newStick = newStick;
        this.newStickName = newStick.getName();
    }

    public String getNewMeatName() {
        return newMeatName;
    }

    public String getNewStickName() {
        return newStickName;
    }

    public void setNewMeatName(String newMeatName) {
        this.newMeatName = newMeatName;
    }

    public void setNewStickName(String newStickName) {
        this.newStickName = newStickName;
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public ArrayList<Barbecue> getHelp() {
        return help;
    }

    public void setHelp(ArrayList<Barbecue> help) {
        this.help = help;
    }

}