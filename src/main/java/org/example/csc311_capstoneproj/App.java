package org.example.csc311_capstoneproj;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.csc311_capstoneproj.utils.SceneManager;

public class App extends Application {
    @Override
    public void start(Stage primarystage) {
        SceneManager.setStage(primarystage);
        SceneManager.switchTo("login.fxml");
        primarystage.setTitle("Movie Diary");
    }

    public static void main(String[] args) {
        launch();
    }
}