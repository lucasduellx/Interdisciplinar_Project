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
import javafx.geometry.Side;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;

public class ConfigController {
    
    @FXML private TableView<Meat> meatTable;
    @FXML private TableColumn<Meat, String> meatCol;
    @FXML private TableColumn<Meat, String> pointCol;
    // @FXML private TableView<Meat> meatTableSelected;
    // @FXML private TableColumn<Meat, String> meatColSelected;
    // @FXML private TableColumn<Meat, String> pointColSelected;

    @FXML private TableView<Stick> stickTable;
    @FXML private TableColumn<Stick, String> nameCol;
    // @FXML private TableView<Stick> stickTableSelected;
    // @FXML private TableColumn<Stick, String> nameColSelected;

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
        // meatColSelected.setCellValueFactory(
        //     new PropertyValueFactory<>("name"));
        // pointColSelected.setCellValueFactory(
        //     new PropertyValueFactory<>("point"));

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

        // meatTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Meat>() {

        //     @Override
        //     public void changed(ObservableValue<? extends Meat> arg0, Meat arg1, Meat arg2) {
        //         Boolean test = false;
        //         for (Stick stick : barbecue.getStick_meat().keySet()) {
        //             if(barbecue.getStick_meat().get(stick) == null){
        //                 test = true;
        //                 barbecue.addMap(stick, arg2);
        //                 System.out.println(barbecue.getStick_meat());
        //                 Barbecue aux = new Barbecue();
        //                 aux.setNewMeat(arg2);
        //                 aux.setNewStick(stick);
        //                 barbecueItens.add(aux);
        //                 barbecueTable.setItems(FXCollections.observableArrayList(barbecueItens));
        //                 break;
        //             }
        //         }
        //         if(!test) {
        //             meatTable.getSelectionModel().clearSelection();
        //             showAlert("Espeto não disponivel","Nenhum espeto disponivel", Alert.AlertType.ERROR);
        //         }
        //         // if(!meatTableSelected.getItems().contains(arg2)) {
        //         //     meatTableSelected.getItems().add(arg2);
        //         //     for (Stick stick : barbecue.getStick_meat().keySet()) {
        //         //         if(barbecue.getStick_meat().get(stick) == null){
        //         //             barbecue.addMap(stick, arg2);
        //         //             System.out.println(barbecue.getStick_meat());
        //         //             Barbecue aux = new Barbecue();
        //         //             aux.setNewMeat(arg2);
        //         //             aux.setNewStick(stick);
        //         //             barbecueItens.add(aux);
        //         //             barbecueTable.setItems(FXCollections.observableArrayList(barbecueItens));
        //         //             break;
        //         //         }
        //         //     }
        //         // }
        //     }
        // });


        // meatTableSelected.setRowFactory(tv -> {
        //     TableRow<Meat> row = new TableRow<>();
        //     row.setOnMouseClicked(event -> {
        //         if (! row.isEmpty() && event.getButton()==MouseButton.PRIMARY) {
        //             Meat clickedRow = row.getItem();
        //             meatTableSelected.getItems().remove(clickedRow);
        //         }
        //     });
        //     return row ;
        // });

           
        nameCol.setCellValueFactory(
            new PropertyValueFactory<>("name"));
        // nameColSelected.setCellValueFactory(
        //     new PropertyValueFactory<>("name"));
        stickTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Stick>() {

            @Override
            public void changed(ObservableValue<? extends Stick> arg0, Stick arg1, Stick arg2) {
                if(!barbecue.getStick_meat().containsKey(arg2)) barbecue.addMap(arg2, null);
                // if(!stickTableSelected.getItems().contains(arg2)){ 
                //     stickTableSelected.getItems().add(arg2);
                //     barbecue.addMap(arg2, null);
                // }
            }
        });
        // stickTableSelected.setRowFactory(tv -> {
        //     TableRow<Stick> row = new TableRow<>();
        //     row.setOnMouseClicked(event -> {
        //         if (! row.isEmpty() && event.getButton()==MouseButton.PRIMARY) {
        //             Stick clickedRow = row.getItem();
        //             stickTableSelected.getItems().remove(clickedRow);
        //             barbecue.removeMap(clickedRow);
        //         }
        //     });
        //     return row ;
        // });

        meatTable.setItems(FXCollections.observableArrayList(MeatDAO.getInstance().getMeats(UserSession.getSessionUser())));
        stickTable.setItems(FXCollections.observableArrayList(StickDAO.getInstance().getSticks(UserSession.getSessionUser())));
    }

    @FXML
    private void backScreen() throws Exception {
        App.setRoot("principal");
    }

    @FXML
    private void requestBarbecue() throws Exception {
        App.setRoot("barbecue");
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