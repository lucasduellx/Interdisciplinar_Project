package com.intellichurras;

import java.util.ArrayList;

import com.dao.MeatDAO;
import com.dao.StickDAO;
import com.dao.UserSession;
import com.helper.Barbecue;
import com.helper.Meat;
import com.helper.Stick;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;

public class ConfigController {
    
    @FXML private TableView<Meat> meatTable;
    @FXML private TableColumn<Meat, String> meatCol;
    @FXML private TableColumn<Meat, String> pointCol;

    @FXML private TableView<Stick> stickTable;
    @FXML private TableColumn<Stick, String> nameCol;

    @FXML private TableView<Barbecue> barbecueTable;
    @FXML private TableColumn<Barbecue, Stick> nameBarbecueCol;
    @FXML private TableColumn<Barbecue, Meat> meatBarbecueCol;

    private Barbecue barbecue;
    private ArrayList<Barbecue> barbecueItens = new ArrayList<Barbecue>();

    @FXML
    private void initialize() throws Exception{
        barbecue = Barbecue.getInstance();

        nameBarbecueCol.setCellValueFactory(
            new PropertyValueFactory<>("newStickName"));
        meatBarbecueCol.setCellValueFactory(
            new PropertyValueFactory<>("newMeatName"));

        meatCol.setCellValueFactory(
            new PropertyValueFactory<>("name"));
        pointCol.setCellValueFactory(
            new PropertyValueFactory<>("point"));

        meatTable.setRowFactory(tv -> {
            TableRow<Meat> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (! row.isEmpty() && event.getButton()==MouseButton.PRIMARY) {
                    Meat clickedRow = row.getItem();
                    Boolean test = false;
                    for (Stick stick : barbecue.getStick_meat().keySet()) {
                        if(barbecue.getStick_meat().get(stick) == null){
                            test = true;
                            barbecue.addMap(stick, clickedRow);
                            Barbecue aux = new Barbecue(stick, clickedRow);
                            barbecueItens.add(aux);
                            barbecueTable.setItems(FXCollections.observableArrayList(barbecueItens));
                            barbecue.setHelp(barbecueItens);
                            break;
                        }
                    }
                    if(!test) {
                        meatTable.getSelectionModel().clearSelection();
                        showAlert("Espeto não selecionado ou disponivel","Nenhum espeto selecionado ou disponivel", Alert.AlertType.ERROR);
                    }
                }
            });
            return row ;
        });
           
        nameCol.setCellValueFactory(
            new PropertyValueFactory<>("name"));
        stickTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Stick>() {

            @Override
            public void changed(ObservableValue<? extends Stick> arg0, Stick arg1, Stick arg2) {
                if(!barbecue.getStick_meat().containsKey(arg2)) barbecue.addMap(arg2, null);
            }
        });

        meatTable.setItems(FXCollections.observableArrayList(MeatDAO.getInstance().getMeats(UserSession.getSessionUser())));
        stickTable.setItems(FXCollections.observableArrayList(StickDAO.getInstance().getSticks(UserSession.getSessionUser())));
    }

    @FXML
    private void backScreen() throws Exception {
        App.setRoot("principal");
    }

    @FXML
    private void requestBarbecue() throws Exception {
        if(barbecueTable.getItems().size() > 0) App.setRoot("barbecue");
        else showAlert("Relacionamento insuficiente","Relacione ao menos um espeto com uma carne", Alert.AlertType.ERROR);
    }

    @FXML
    private void requestHistory() throws Exception {
        App.setRoot("history");
    }

    private void showAlert(String Title,String Content,Alert.AlertType Type) {
        Platform.runLater(new Runnable() {
          public void run() {
              Alert alert = new Alert(Type);
              alert.setTitle(Title);
              alert.setContentText(Content);
              alert.showAndWait();
          }
        });
    }

}