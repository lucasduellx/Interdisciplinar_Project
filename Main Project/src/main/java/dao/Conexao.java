package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static Conexao conexao;

    private Conexao(){}

    public static Conexao getInstance(){
        if(conexao == null){
            conexao = new Conexao();
        }
        return conexao;
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/intellichurras","root","admin");
        return connection;
    }
}