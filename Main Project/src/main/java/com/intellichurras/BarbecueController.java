package com.intellichurras;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
// import java.util.HashMap;
// import java.util.Map;

import com.helper.Barbecue;

import org.json.JSONObject;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class BarbecueController {
    
    @FXML private TableView<Barbecue> barbecueTable;
    @FXML private TableColumn<Barbecue,String> stickCol;
    @FXML private TableColumn<Barbecue,Double> tempCol;
    @FXML private TableColumn<Barbecue,String> statusCol;
    //private Map<Integer,Double> temps = new HashMap<Integer,Double>();

    @FXML
    private void initialize() throws Exception{

        new Thread() {

            @Override
            public void run() {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create("http://127.0.0.1:5000"))
                        .build();
        
                HttpResponse<String> response;
                try {
                    response = client.send(request,
                            HttpResponse.BodyHandlers.ofString());
                    //System.out.println(response.body());
                    JSONObject jsonObject = new JSONObject(response.body());
                    for (int i = 0; i < jsonObject.getJSONArray("answer").length(); i++) {
                        JSONObject temp = jsonObject.getJSONArray("answer").getJSONObject(i);
                        System.out.println(temp.getInt("id"));
                        System.out.println(temp.getDouble("temperature"));
                    }

                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        

        
            }
        }.start();

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
    
}