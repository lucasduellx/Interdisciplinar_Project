package com.intellichurras;

import java.io.IOException;
import javafx.fxml.FXML;

public class PrincipalController {

    @FXML
    private void backToLogin() throws IOException {
        App.setRoot("login");
    }
}