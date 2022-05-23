package com.intellichurras;

import java.io.IOException;
import java.sql.SQLException;

import com.dao.StickDAO;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;


public class StorageController {
    @FXML private TextField stickField;
    @FXML private TextField idField;

    @FXML
    private void backScreen() throws Exception{
        App.setRoot("stick");
    }

    @FXML
    private void registerStick() throws ClassNotFoundException, SQLException, IOException{
        String stick = stickField.getText();
        
        Integer id;

        if(stick == "" || idField.getText().equals("")){
            showAlert("Erro Encontrado","Preenchimento","Preencha os todos os campos",Alert.AlertType.ERROR);
            return;
        }

        try {
            id = Integer.parseInt(idField.getText());
        } catch (Exception e) {
            showAlert("Erro Encontrado","Conversão de dados","Digite um ID válido(apenas numeros)!",Alert.AlertType.ERROR);
            return;
        }
        
        if(StickDAO.getInstance().registerStick(id,stick)){
            showAlert("Sucesso!","Novo Espeto","Espeto cadastrado com sucesso!",Alert.AlertType.INFORMATION);
            App.setRoot("stick");
        }
        else{
            showAlert("Erro Encontrado","Verificação de dados","Espeto utilzado já existente no sistema",Alert.AlertType.ERROR);
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