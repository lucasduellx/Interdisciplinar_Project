package com.intellichurras;

import java.io.IOException;
import javafx.fxml.FXML;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML private TextField userField;
    @FXML private PasswordField passField;


    @FXML
    private void requestLogin() throws IOException {
        String user = userField.getText();
        String pass = passField.getText();
        System.out.println(user);
        System.out.println(pass);
        if(user.equals("admin")){
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
