package org.example.csc311_capstoneproj.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.function.Consumer;

public class SceneManager {
    private static Stage primaryStage;

    public static void setStage(Stage stage) {
        primaryStage = stage;
    }

    /**
     * Simple scene switch without passing data.
     */
    public static void switchTo(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource("/org/example/csc311_capstoneproj/fxml/" + fxmlFile));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Image appIcon = new Image(SceneManager.class.getResourceAsStream("/org/example/csc311_capstoneproj/fxml/images/MovieDiaryLogoIcon.png"));
            primaryStage.getIcons().add(appIcon);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Scene switch that gives access to the controller to pass data.
     * @param fxmlFile The FXML file to load.
     * @param controllerConsumer A lambda that receives the controller.
     * @param <T> The controller type.
     */
    public static <T> void switchToWithController(String fxmlFile, Consumer<T> controllerConsumer) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource("/org/example/csc311_capstoneproj/fxml/" + fxmlFile));
            Parent root = loader.load();
            T controller = loader.getController();
            controllerConsumer.accept(controller); // Pass the controller to the caller
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
