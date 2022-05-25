package com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.helper.Barbecue;

public class BarbecueDAO {
    private static BarbecueDAO barbecue;

    private PreparedStatement prstmt = null;
    private ResultSet rs = null;
    private String query = null;

    private BarbecueDAO(){}

    public static BarbecueDAO getInstance(){
        if(barbecue == null){
            barbecue = new BarbecueDAO();
        }
        return barbecue;
    }

    public ArrayList<Barbecue> getBarbecues(String user) throws Exception{
        ArrayList<Barbecue> barbecues = new ArrayList<Barbecue>();

        query = "SELECT * FROM barbecues WHERE user = ?";
        prstmt = com.dao.Conexao.getInstance().getConnection().prepareStatement(query);
        prstmt.setString(1, user);
        rs = prstmt.executeQuery();
        
        com.dao.Conexao.getInstance().getConnection().close();

        while(rs.next()){
            Barbecue barbecue = new Barbecue(rs.getInt("quantidade_pessoas"),rs.getDouble("quantidade_carne"));

            barbecue.setFinalStatus(rs.getString("status"));
            barbecue.setDate(rs.getDate("data"));

            barbecues.add(barbecue);
        }

        return barbecues;
    }
    
    public Boolean registerBarbecue(Barbecue barb) throws ClassNotFoundException, SQLException{

        String user = UserSession.getSessionUser();
        long data = barb.getDate().getTime();
        java.sql.Date dataSql = new java.sql.Date(data);
        query = "INSERT INTO barbecues(quantidade_pessoas,quantidade_carne,data,status,user) VALUES(?,?,?,?,?)";
        prstmt = com.dao.Conexao.getInstance().getConnection().prepareStatement(query);
        prstmt.setInt(1, barb.getPeoples());
        prstmt.setDouble(2, barb.getWeightMeat());
        prstmt.setDate(3, dataSql);
        prstmt.setString(4, barb.getFinalStatus());
        prstmt.setString(5, user);
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