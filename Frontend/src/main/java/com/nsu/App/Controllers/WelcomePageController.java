package com.nsu.App.Controllers;

import com.nsu.App.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomePageController {
    @FXML
    Button buttonTables;

    @FXML
    Button buttonRequest;

    @FXML
    void initialize(){
        buttonTables.setOnAction(actionEvent -> {
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("Makets/TableStage.fxml"));
            try {
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = (Stage) buttonTables.getScene().getWindow();
                stage.setScene(scene);
                stage.maximizedProperty();
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        buttonRequest.setOnAction(actionEvent -> {
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("Makets/RequestStage.fxml"));
            try {
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = (Stage) buttonRequest.getScene().getWindow();
                stage.setScene(scene);
                stage.setMaximized(true);
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}
