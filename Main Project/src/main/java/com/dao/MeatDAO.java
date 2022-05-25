package com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.business.MeatBUSINESS;
import com.helper.Meat;

public class MeatDAO {
    private static MeatDAO meat;

    private PreparedStatement prstmt = null;
    private ResultSet rs = null;
    private String query = null;

    private MeatDAO(){}

    public static MeatDAO getInstance(){
        if(meat == null){
            meat = new MeatDAO();
        }
        return meat;
    }

    public Meat getMeat(String name) throws Exception{
        Meat meat = null;

        query = "SELECT * FROM meats WHERE name = ?";
        prstmt = com.dao.Conexao.getInstance().getConnection().prepareStatement(query);
        prstmt.setString(1, name);
        rs = prstmt.executeQuery();
        
        com.dao.Conexao.getInstance().getConnection().close();

        while(rs.next()){
            meat = new Meat();
            
            meat.setName(rs.getString("name"));
            meat.setType(rs.getString("type"));
            meat.setPoint(rs.getString("point"));
            meat.setUser(rs.getString("user"));
            meat.setTemp_min(rs.getDouble("temp_min"));
            meat.setTemp_max(rs.getDouble("temp_max"));

        }

        return meat;
    }

    public ArrayList<Meat> getMeats(String user) throws Exception{
        ArrayList<Meat> meats = new ArrayList<Meat>();

        query = "SELECT * FROM meats WHERE user = ?";
        prstmt = com.dao.Conexao.getInstance().getConnection().prepareStatement(query);
        prstmt.setString(1, user);
        rs = prstmt.executeQuery();
        
        com.dao.Conexao.getInstance().getConnection().close();

        while(rs.next()){
            Meat meat = new Meat();

            meat.setName(rs.getString("name"));
            meat.setType(rs.getString("type"));
            meat.setPoint(rs.getString("point"));
            meat.setUser(rs.getString("user"));
            meat.setTemp_min(rs.getDouble("temp_min"));
            meat.setTemp_max(rs.getDouble("temp_max"));

            meats.add(meat);
        }

        return meats;
    }
    
    public Boolean registerMeat(String meat,String type,String point,Double min_temp,Double max_temp) throws ClassNotFoundException, SQLException{

        String user = UserSession.getSessionUser();
        query = "INSERT INTO meats(name,type,point,temp_min,temp_max,user) VALUES(?,?,?,?,?,?)";
        if(!type.equals("Outro")){
            ArrayList<Double> temps = MeatBUSINESS.checkTemp(point);
            min_temp = temps.get(0);
            max_temp = temps.get(1);
        }
        prstmt = com.dao.Conexao.getInstance().getConnection().prepareStatement(query);
        prstmt.setString(1, meat);
        prstmt.setString(2, type);
        prstmt.setString(3, point);
        prstmt.setDouble(4, min_temp);
        prstmt.setDouble(5, max_temp);
        prstmt.setString(6, user);
        int result = 0;
        try{
            result = prstmt.executeUpdate();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        com.dao.Conexao.getInstance().getConnection().close();
        if(result == 1) return true;
        return false;
    }

    public Boolean updateMeat(String meat,String user,String point,Character updateType) throws Exception {
        if(updateType == 'P'){
            ArrayList<Double> temps = MeatBUSINESS.checkTemp(point);
            query = "UPDATE meats SET point=?,temp_min=?,temp_max =? WHERE name=? and user=?";
            prstmt = com.dao.Conexao.getInstance().getConnection().prepareStatement(query);
            prstmt.setString(1, point);
            prstmt.setDouble(2, temps.get(0));
            prstmt.setDouble(3, temps.get(1));
            prstmt.setString(4, meat);
            prstmt.setString(5, user);
        }
        else {
            //NÃO FUNCIONA NO MYSQL
            query = "UPDATE meats SET name=? WHERE user=?";
            prstmt = com.dao.Conexao.getInstance().getConnection().prepareStatement(query);
            prstmt.setString(1, meat);
            prstmt.setString(2, user);
        }
        int result = 0;
        try{
            result = prstmt.executeUpdate();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        com.dao.Conexao.getInstance().getConnection().close();
        if(result == 1) return true;
        return false;
    }
}