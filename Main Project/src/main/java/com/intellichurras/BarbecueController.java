package com.intellichurras;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.business.BarbecueBUSINESS;
import com.dao.BarbecueDAO;
import com.helper.Barbecue;

import org.json.JSONObject;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;


public class BarbecueController {
    
    @FXML private TextField weightField;
    @FXML private TextField peopleField;
    @FXML private ComboBox<String> finalStatusField;
    @FXML private TableView<Barbecue> barbecueTable;
    @FXML private TableColumn<Barbecue,String> stickCol;
    @FXML private TableColumn<Barbecue,Double> tempCol;
    @FXML private TableColumn<Barbecue,String> statusCol;
    private HttpClient client = HttpClient.newHttpClient();
    private HttpResponse<String> response;

    @FXML
    private void initialize() throws Exception{

        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

        Runnable periodicTask = new Runnable() {
            public void run() {
                try {
                    HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://127.0.0.1:5000"))
                    .build();
                    response = client.send(request,
                            HttpResponse.BodyHandlers.ofString());
                    //System.out.println(response.body());
                    JSONObject jsonObject = new JSONObject(response.body());
                    for (int i = 0; i < jsonObject.getJSONArray("answer").length(); i++) {
                        JSONObject temp = jsonObject.getJSONArray("answer").getJSONObject(i);
                        
                        for(Barbecue barb : Barbecue.getInstance().getHelp()){
                            if(barb.getNewStick().getId() == temp.getInt("id")) {
                                barb.setTemp(temp.getDouble("temperature")); 
                                barb.setStatus(BarbecueBUSINESS.checkCurrentTemp(barb.getNewMeat(), temp.getDouble("temperature")));
                                break;
                            }
                        }
                    }
                    barbecueTable.getItems().clear();
                    barbecueTable.setItems(FXCollections.observableArrayList(Barbecue.getInstance().getHelp()));

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        executor.scheduleAtFixedRate(periodicTask, 5, 20, TimeUnit.SECONDS);

        stickCol.setCellValueFactory(
            new PropertyValueFactory<>("newStickName"));
        tempCol.setCellValueFactory(
            new PropertyValueFactory<>("temp"));
        statusCol.setCellValueFactory(
            new PropertyValueFactory<>("status"));
        barbecueTable.setItems(FXCollections.observableArrayList(Barbecue.getInstance().getHelp()));
    }

    @FXML
    private void backScreen() throws Exception {
        App.setRoot("config");
    }
    
    @FXML
    private void Save() throws Exception {
        
        Double weight = 0.0;
        Integer people = 0;
        try {
            people = Integer.parseInt(peopleField.getText());
            weight = Double.parseDouble(weightField.getText());
        } catch (Exception e) {
            showAlert("Numero Invalido", "Numero digitado é invalido", "Digite um numero inteiro de pessoas e peso total da carne em Kg!", AlertType.ERROR);
            return;
        }
        String status = (String) finalStatusField.getValue();
        Barbecue barb = new Barbecue(people, weight);
        barb.setFinalStatus(status);
        barb.setDate(new Date());
        BarbecueDAO.getInstance().registerBarbecue(barb);
        showAlert("Histórico Criado", "Churrasco Salvo", "Churrasco e seus dados foram salvos com sucesso!", AlertType.INFORMATION);
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