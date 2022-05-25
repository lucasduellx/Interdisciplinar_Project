package com.intellichurras;

import java.io.IOException;

import com.dao.MeatDAO;
import com.dao.UserSession;
import com.helper.Meat;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;

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
 
        //Init points list name
        ObservableList<String> listItems = FXCollections.observableArrayList();
        listItems.add("Mal Passada");
        listItems.add("Ao ponto para Mal Passada");
        listItems.add("Ao ponto");
        listItems.add("Ao ponto para Bem Passada");
        listItems.add("Bem Passada");

        //Init config table
        nameCol.setCellValueFactory(
                new PropertyValueFactory<>("name"));
        typeCol.setCellValueFactory(
                new PropertyValueFactory<>("type"));
        pointCol.setCellValueFactory(
                new PropertyValueFactory<>("point"));
        pointCol.setCellFactory(
                ComboBoxTableCell.forTableColumn(listItems)
        );
        minimaCol.setCellValueFactory(
                new PropertyValueFactory<>("temp_min"));
        maximaCol.setCellValueFactory(
                new PropertyValueFactory<>("temp_max"));
        meatTable.setItems(FXCollections.observableArrayList(MeatDAO.getInstance().getMeats(UserSession.getSessionUser())));
        
        pointCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Meat, String>>() {

                @Override
                public void handle(CellEditEvent<Meat, String> event){
                        try {
                                if(MeatDAO.getInstance().updateMeat(event.getRowValue().getName(), event.getRowValue().getUser(), event.getNewValue(), 'P')){
                                        meatTable.setItems(FXCollections.observableArrayList(MeatDAO.getInstance().getMeats(UserSession.getSessionUser())));
                                }
                                else{
                                        showAlert("Erro Encontrado", "Falha na atualização!", "Foi encontrado um erro na atualização, tente novamente!", AlertType.ERROR);
                                }
                        } catch (Exception e) {
                                showAlert("Erro Encontrado", "Falha na atualização!", "Falha na conexão com o banco de dados!", AlertType.ERROR);
                        }
                }
        });

    }

    @FXML
    private void registerMeat() throws IOException {
        App.setRoot("freezer");
    }
    
    @FXML
    private void backScreen() throws IOException {
        App.setRoot("principal");
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