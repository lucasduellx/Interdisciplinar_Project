package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import helper.Meat;

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

    public ArrayList<Meat> getMeats(String user) throws Exception{
        ArrayList<Meat> meats = new ArrayList<Meat>();

        query = "SELECT * FROM meats WHERE user = ?";
        prstmt = dao.Conexao.getInstance().getConnection().prepareStatement(query);
        prstmt.setString(1, user);
        rs = prstmt.executeQuery();
        
        dao.Conexao.getInstance().getConnection().close();

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
        if(type != "Outro"){
            return false; // FAZER REGRA DE TEMPERATURA
        }
        prstmt = dao.Conexao.getInstance().getConnection().prepareStatement(query);
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
        dao.Conexao.getInstance().getConnection().close();
        if(result == 1) return true;
        return false;
    }
}