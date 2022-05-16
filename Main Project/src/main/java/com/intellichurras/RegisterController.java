package com.intellichurras;

import business.UserBUSINESS;
import dao.UserDAO;
import dao.UserSession;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;


public class RegisterController {

    @FXML private TextField userField;
    @FXML private TextField secretAnswerField;
    @FXML private PasswordField passField;
    @FXML private ComboBox<String> secretQuestionField;

    
    @FXML
    private void requestRegister() throws Exception {
        String user = userField.getText();
        String pass = passField.getText();
        String answer = secretAnswerField.getText();
        String question = (String) secretQuestionField.getValue();

        if(!UserBUSINESS.checkPass(pass)) 
        {
            showAlert("Erro Encontrado","Verificação de coesão","Senha precisa ter Letra maiúsculo e minúsculo e caracter especial!",Alert.AlertType.ERROR);
            return;
        }

        if(!UserBUSINESS.checkUser(user) && UserDAO.getInstance().registerUser(user, pass,question,answer)){
            showAlert("Sucesso!","Novo Usuario","Usuario cadastrado com sucesso!",Alert.AlertType.INFORMATION);
            UserSession.getInstance(user);
            App.setRoot("principal");
        }
        else{
            showAlert("Erro Encontrado","Verificação de dados","Usuario já cadastrado!",Alert.AlertType.ERROR);
        }
    }

    private void showAlert(String Title,String Header,String Content,Alert.AlertType Type) {
        Platform.runLater(new Runnable() {
          public void run() {
              Alert alert = new Alert(Type);
              alert.setTitle(Title);
              alert.setHeaderText(Header);
              alert.setContentText(Content);
              alert.showAndWait();
          }
        });
    }
}