package org.example.csc311_capstoneproj;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("resources/fxml/login.fxml"));
        Parent welcomeRoot = fxmlLoader.load();
        Scene welcomeScene = new Scene(welcomeRoot);
        stage.setTitle("Movie Diary");
        stage.setScene(welcomeScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}