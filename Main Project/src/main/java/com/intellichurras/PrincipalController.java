package com.intellichurras;

import java.io.IOException;

import com.dao.UserSession;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PrincipalController {

    @FXML private Label currentUser;

    @FXML
    private void initialize(){
        currentUser.setText("Bem Vindo(a), " + UserSession.getSessionUser());
    }


    @FXML
    private void logout() throws IOException {
        UserSession.clearSession();
        App.setRoot("login");
    }

    @FXML
    private void requestMeat() throws IOException{
        App.setRoot("meat");
    }

    @FXML
    private void requestStick() throws IOException{
        App.setRoot("stick");
    }

    @FXML
    private void requestBarbecue() throws IOException {
        System.out.println("Maminha");
    }
}