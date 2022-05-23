package com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.helper.Stick;

public class StickDAO {
    private static StickDAO stick;

    private PreparedStatement prstmt = null;
    private ResultSet rs = null;
    private String query = null;

    private StickDAO(){}

    public static StickDAO getInstance(){
        if(stick == null){
            stick = new StickDAO();
        }
        return stick;
    }

    public Stick getStick(String name) throws Exception{
        Stick stick = null;

        query = "SELECT * FROM sticks WHERE name = ?";
        prstmt = com.dao.Conexao.getInstance().getConnection().prepareStatement(query);
        prstmt.setString(1, name);
        rs = prstmt.executeQuery();
        
        com.dao.Conexao.getInstance().getConnection().close();

        while(rs.next()){
            stick = new Stick();
            
            stick.setName(rs.getString("name"));
            stick.setId(rs.getInt("id"));
            stick.setUser(rs.getString("user"));

        }

        return stick;
    }

    public ArrayList<Stick> getSticks(String user) throws Exception{
        ArrayList<Stick> sticks = new ArrayList<Stick>();

        query = "SELECT * FROM sticks WHERE user = ?";
        prstmt = com.dao.Conexao.getInstance().getConnection().prepareStatement(query);
        prstmt.setString(1, user);
        rs = prstmt.executeQuery();
        
        com.dao.Conexao.getInstance().getConnection().close();

        while(rs.next()){
            Stick stick = new Stick();

            stick.setName(rs.getString("name"));
            stick.setId(rs.getInt("id"));
            stick.setUser(rs.getString("user"));

            sticks.add(stick);
        }

        return sticks;
    }
    
    public Boolean registerStick(Integer id,String stick) throws ClassNotFoundException, SQLException{

        String user = UserSession.getSessionUser();
        query = "INSERT INTO sticks(id,name,user) VALUES(?,?,?)";

        prstmt = com.dao.Conexao.getInstance().getConnection().prepareStatement(query);
        prstmt.setInt(1, id);
        prstmt.setString(2, stick);
        prstmt.setString(3, user);
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