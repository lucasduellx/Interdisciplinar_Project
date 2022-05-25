package com.intellichurras;

import java.util.Date;

import com.dao.BarbecueDAO;
import com.dao.UserSession;
import com.helper.Barbecue;

import javafx.collections.FXCollections;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class HistoryController {

    @FXML
    private TableView<Barbecue> historyTable;
    @FXML
    private TableColumn<Barbecue, Integer> peopleCol;
    @FXML
    private TableColumn<Barbecue, Double> pesoCol;
    @FXML
    private TableColumn<Barbecue, Date> dataCol;
    @FXML
    private TableColumn<Barbecue, String> statusCol;

    @FXML
    private void initialize() throws Exception{

        //Init config table
        peopleCol.setCellValueFactory(
                new PropertyValueFactory<>("peoples"));
        pesoCol.setCellValueFactory(
                new PropertyValueFactory<>("weightMeat"));
        dataCol.setCellValueFactory(
                new PropertyValueFactory<>("date"));
        statusCol.setCellValueFactory(
                new PropertyValueFactory<>("finalStatus"));
        historyTable.setItems(FXCollections.observableArrayList(BarbecueDAO.getInstance().getBarbecues(UserSession.getSessionUser())));

    }

    @FXML
    private void backScreen() throws Exception{
        App.setRoot("principal");
    }
    
}