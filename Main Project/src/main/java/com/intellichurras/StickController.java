package com.intellichurras;

import java.io.IOException;

import com.dao.StickDAO;
import com.dao.UserSession;
import com.helper.Stick;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class StickController {

    @FXML 
    private Label currentUser;
    @FXML
    private TableView<Stick> stickTable;
    @FXML
    private TableColumn<Stick, String> nameCol;
    @FXML
    private TableColumn<Stick, Integer> idCol;

    @FXML
    private void initialize() throws Exception{

        //Init config table
        nameCol.setCellValueFactory(
                new PropertyValueFactory<>("name"));
        idCol.setCellValueFactory(
                new PropertyValueFactory<>("id"));
        stickTable.setItems(FXCollections.observableArrayList(StickDAO.getInstance().getSticks(UserSession.getSessionUser())));

    }

    @FXML
    private void registerStick() throws IOException {
        App.setRoot("storage");
    }
    
    @FXML
    private void backScreen() throws IOException {
        App.setRoot("principal");
    }
}