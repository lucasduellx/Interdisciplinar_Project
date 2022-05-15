package com.intellichurras;

import java.io.IOException;

import dao.MeatDAO;
import dao.UserSession;
import helper.Meat;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class MeatController {

    @FXML 
    private Label currentUser;
    @FXML
    private TableView<Meat> meatTable;
    @FXML
    private TableColumn<Meat, String> nameCol;
    @FXML
    private TableColumn<Meat, String> typeCol;
    @FXML
    private TableColumn<Meat, String> pointCol;
    @FXML
    private TableColumn<Meat, String> tempCol;
    @FXML
    private TableColumn<Meat, Double> minimaCol;
    @FXML
    private TableColumn<Meat, Double> maximaCol;

    @FXML
    private void initialize() throws Exception{
        nameCol.setCellValueFactory(
                new PropertyValueFactory<>("name"));
        nameCol.setCellFactory(
                TextFieldTableCell.forTableColumn());
        typeCol.setCellValueFactory(
                new PropertyValueFactory<>("type"));
        pointCol.setCellValueFactory(
                new PropertyValueFactory<>("point"));
        minimaCol.setCellValueFactory(
                new PropertyValueFactory<>("temp_min"));
        maximaCol.setCellValueFactory(
                new PropertyValueFactory<>("temp_max"));
        meatTable.setItems(FXCollections.observableArrayList(MeatDAO.getInstance().getMeats(UserSession.getSessionUser())));
    }

    @FXML
    private void registerMeat() throws IOException {
        App.setRoot("freezer");
    }
    
    @FXML
    private void backScreen() throws IOException {
        App.setRoot("principal");
    }
}