package com.intellichurras;


import java.io.IOException;
import java.sql.SQLException;

import dao.MeatDAO;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class FreezerController {
    
    @FXML private TextField meatField;
    @FXML private ComboBox<String> typeField;
    @FXML private ComboBox<String> pointField;
    @FXML private VBox showTemp;
    @FXML private TextField minTemp;
    @FXML private TextField maxTemp;

    @FXML
    private void initialize(){
        showTemp.setVisible(false);
        typeField.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String oldV, String newV) {
                System.out.println("Previous Value: "+ oldV);
                System.out.println("Current Value: "+ newV);
                if(newV.equals("Outro")){
                    showTemp.setVisible(true);
                }
                else{
                    showTemp.setVisible(false);
                }

            }
        });
    }

    @FXML
    private void backScreen() throws Exception{
        App.setRoot("meat");
    }

    @FXML
    private void registerMeat() throws ClassNotFoundException, SQLException, IOException{
        String meat = meatField.getText();
        
        String type = (String) typeField.getValue();
        String point = (String) pointField.getValue();
        Double min_temp;
        Double max_temp;
        try {
            min_temp = Double.parseDouble(minTemp.getText());
            max_temp = Double.parseDouble(maxTemp.getText());
        } catch (Exception e) {
            showAlert("Erro Encontrado","Conversão de dados","Digite numeros validos!",Alert.AlertType.ERROR);
            return;
        }
        
        if(MeatDAO.getInstance().registerMeat(meat, type,point,min_temp,max_temp)){
            showAlert("Sucesso!","Nova Carne","Carne cadastrada com sucesso!",Alert.AlertType.INFORMATION);
            App.setRoot("meat");
        }
        else{
            showAlert("Erro Encontrado","Verificação de dados","Carne já cadastrada!",Alert.AlertType.ERROR);
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