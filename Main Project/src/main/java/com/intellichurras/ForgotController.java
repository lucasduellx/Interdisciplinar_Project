package com.intellichurras;

import dao.UserDAO;
import javafx.fxml.FXML;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class ForgotController {
    
    @FXML private TextField userField;
    @FXML private Label secretQuestionField;
    @FXML private TextField secretAnswerField;
    @FXML private PasswordField secretNewPassField;
    @FXML private PasswordField secretNewPassVerifyField;
    @FXML private VBox showQuestion;
    private String dbAnswer = "";
    private String currentUser = "";

    @FXML
    private void initialize(){
        showQuestion.setVisible(false);
    }

    @FXML
    private void verifyAnswer() throws Exception{
        if(secretAnswerField.getText().equals(dbAnswer)){
            String newPass = secretNewPassField.getText();
            String newPassConfirm = secretNewPassVerifyField.getText();
            if(newPass.equals(newPassConfirm)){
                if(UserDAO.getInstance().updateUser(currentUser, newPass)){
                    showAlert("Sucesso","Senha Alterada!","Sua senha foi alterada com sucesso!",Alert.AlertType.INFORMATION);
                    App.setRoot("login");
                }
                else{
                    showAlert("Erro Encontrado","Falha na conexão!","Erro inesperado, tente novamente mais tarde!",Alert.AlertType.ERROR);
                }
            }
            else{
                showAlert("Erro Encontrado","Verificação de comparação!","Senhas não correspondem!",Alert.AlertType.ERROR);
            }
        }
        else{
            showAlert("Erro Encontrado","Verificação de concordancia!","Resposta Invalida!",Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void requestPassword() throws Exception {
        String user = userField.getText();
        String result = UserDAO.getInstance().checkForgotUser(user);

        if(!result.equals("")){
            secretQuestionField.setText(result.split(";")[0]);
            dbAnswer = result.split(";")[1];
            currentUser = result.split(";")[2];
            showQuestion.setVisible(true);
        }
        else{
            showAlert("Erro Encontrado","Verificação de existencia!","Usuario não encontrado!",Alert.AlertType.ERROR);
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