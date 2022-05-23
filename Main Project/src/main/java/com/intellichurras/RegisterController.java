package com.intellichurras;

import com.business.UserBUSINESS;
import com.dao.UserDAO;
import com.dao.UserSession;

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
    private void backScreen() throws Exception {
        App.setRoot("login");
    }
    
    @FXML
    private void requestRegister() throws Exception {
        String user = userField.getText();
        String pass = passField.getText();
        String answer = secretAnswerField.getText();
        String question = (String) secretQuestionField.getValue();

        if(!UserBUSINESS.checkPass(pass)) 
        {
            showAlert("Erro Encontrado","Verificação de coesão","Senha precisa ter Letra maiúsculo, minúsculo, caracter especial e mais de 8 caracteres!",Alert.AlertType.ERROR);
            return;
        }

        if(!UserBUSINESS.checkUser(user) && UserDAO.getInstance().registerUser(user, pass,question,answer)){
            showAlert("Sucesso!","Novo Usuário","Usuário cadastrado com sucesso!",Alert.AlertType.INFORMATION);
            UserSession.getInstance(user);
            App.setRoot("principal");
        }
        else{
            showAlert("Erro Encontrado","Verificação de dados","Usuário utilizado já existe no sistema",Alert.AlertType.ERROR);
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