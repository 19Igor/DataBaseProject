package com.nsu.App.Viewies;

import com.nsu.App.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.nsu.App.Controllers.WelcomePageController;

import java.io.IOException;

public class WelcomePage {

    public WelcomePage(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("Makets/WelcomeStage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setTitle("Theater.");
        primaryStage.setScene(scene);
        primaryStage.show();


    }
}
