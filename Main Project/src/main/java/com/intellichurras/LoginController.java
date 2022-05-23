package com.intellichurras;

import javafx.fxml.FXML;

import com.dao.UserDAO;
import com.dao.UserSession;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML private TextField userField;
    @FXML private PasswordField passField;

    @FXML
    private void requestPassword() throws Exception{
        App.setRoot("forgot");
    }

    @FXML
    private void requestRegister() throws Exception{
        App.setRoot("register");
    }

    @FXML
    private void requestLogin() throws Exception {
        String user = userField.getText();
        String pass = passField.getText();
        if(UserDAO.getInstance().checkUser(user, pass)){
            // Statement stmt = null;
            // ResultSet rs = null;
            // dao.Conexao con = dao.Conexao.getInstance();
            // Connection conn = con.getConnection();
            // stmt = conn.createStatement();
            // rs = stmt.executeQuery("SELECT user,pass FROM empresa.user LIMIT 1");
            // while(rs.next()){
            //     System.out.println(rs.getString("user"));
            //     System.out.println(rs.getString("pass"));
            // }
            UserSession.getInstance(user);
            App.setRoot("principal");
        }
        else{
            showAlert();
        }
    }

    private void showAlert() {
        Platform.runLater(new Runnable() {
          public void run() {
              Alert alert = new Alert(Alert.AlertType.ERROR);
              alert.setTitle("Erro Encontrado");
              alert.setHeaderText("Verificação de dados");
              alert.setContentText("Usuario ou senha invalidos!");
              alert.showAndWait();
          }
        });
    }
}
