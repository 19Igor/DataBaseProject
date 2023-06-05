package com.nsu.App;

import com.nsu.App.Viewies.WelcomePage;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage primaryStage) throws IOException {

        WelcomePage welcomePage = new WelcomePage(primaryStage);

    }



    public static void main(String[] args) {
        launch();
    }
}