package org.example.csc311_capstoneproj;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.csc311_capstoneproj.utils.SceneManager;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage primarystage) throws IOException {
        SceneManager.setStage(primarystage);
        SceneManager.switchTo("login.fxml");
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("resources/fxml/login.fxml"));
//        Parent welcomeRoot = fxmlLoader.load();
//        Scene welcomeScene = new Scene(welcomeRoot);
        primarystage.setTitle("Movie Diary");
//        primarystage.setScene(welcomeScene);
//        primarystage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}