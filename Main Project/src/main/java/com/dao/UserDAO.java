package com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.Statement;

import com.helper.Crypto;

public class UserDAO {
    private static UserDAO user;

    //private Statement stmt = null;
    private PreparedStatement prstmt = null;
    private ResultSet rs = null;
    private String query = null;

    private UserDAO(){}

    public static UserDAO getInstance(){
        if(user == null){
            user = new UserDAO();
        }
        return user;
    }

    public Boolean checkUserExist(String testuser) throws Exception{

        query = "SELECT user FROM users WHERE user = ?";
        prstmt = com.dao.Conexao.getInstance().getConnection().prepareStatement(query);
        prstmt.setString(1, testuser);
        rs = prstmt.executeQuery();
        
        com.dao.Conexao.getInstance().getConnection().close();

        String DBuser = "";

        while(rs.next()){
            DBuser = rs.getString("user");
        }
        if(DBuser.equals(testuser)) return true;
        return false;
    }

    public Boolean checkUser(String testuser,String testpass) throws Exception{

        testpass = Crypto.encrypt(testpass);
        query = "SELECT user,pass FROM users WHERE user = ? and pass = ?";
        prstmt = com.dao.Conexao.getInstance().getConnection().prepareStatement(query);
        prstmt.setString(1, testuser);
        prstmt.setString(2, testpass);
        rs = prstmt.executeQuery();
        
        com.dao.Conexao.getInstance().getConnection().close();

        String DBuser = "";
        String DBpass = "";

        while(rs.next()){
            DBuser = rs.getString("user");
            DBpass = rs.getString("pass");
        }
        if(DBuser.equals(testuser)  && DBpass.equals(testpass)) return true;
        return false;
    }

    public Boolean registerUser(String newuser,String newpass,String question,String answer) throws Exception{

        newpass = Crypto.encrypt(newpass);
        query = "INSERT INTO users(user,pass,question,answer) VALUES(?,?,?,?)";
        prstmt = com.dao.Conexao.getInstance().getConnection().prepareStatement(query);
        prstmt.setString(1, newuser);
        prstmt.setString(2, newpass);
        prstmt.setString(3, question);
        prstmt.setString(4, answer);
        int result = 0;
        result = prstmt.executeUpdate();
        com.dao.Conexao.getInstance().getConnection().close();
        if(result == 1) return true;
        return false;
    }

    public Boolean updateUser(String user,String password) throws Exception{

        password = Crypto.encrypt(password);
        query = "UPDATE users SET pass = ? WHERE user = ?";
        prstmt = com.dao.Conexao.getInstance().getConnection().prepareStatement(query);
        prstmt.setString(1, password);
        prstmt.setString(2, user);
        
        int result = prstmt.executeUpdate();
        com.dao.Conexao.getInstance().getConnection().close();
        if(result == 1) return true;
        return false;
    }

    public String checkForgotUser(String forgotuser) throws Exception{

        query = "SELECT user,pass,question,answer FROM users WHERE user = ?";
        prstmt = com.dao.Conexao.getInstance().getConnection().prepareStatement(query);
        prstmt.setString(1, forgotuser);
        
        rs = prstmt.executeQuery();
        com.dao.Conexao.getInstance().getConnection().close();
        String user = "";
        String question = "";
        String answer = "";
        String result = "";
        while(rs.next()){
            question = rs.getString("question");
            answer = rs.getString("answer");
            user = rs.getString("user");
            result = question + ";" + answer+";"+user;
        }
        return result;
    }

}