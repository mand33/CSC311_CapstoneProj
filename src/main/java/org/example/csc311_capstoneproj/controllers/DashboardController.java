package org.example.csc311_capstoneproj.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.example.csc311_capstoneproj.utils.SceneManager;

public class DashboardController {

    @FXML
    private Button addAMovieButton;

    @FXML
    private Button libraryButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Button reviewButton;

    @FXML
    void addMovieScreen(ActionEvent event) {
        SceneManager.switchTo("addamovie.fxml");

    }

    @FXML
    void libraryScreen(ActionEvent event) {
        SceneManager.switchTo("library.fxml");

    }

    @FXML
    void logout(ActionEvent event) {
        SceneManager.switchTo("login.fxml");

    }

    @FXML
    void reviewsScreen(ActionEvent event) {

    }

}
