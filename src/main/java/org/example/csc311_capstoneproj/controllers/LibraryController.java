package org.example.csc311_capstoneproj.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import org.example.csc311_capstoneproj.utils.SceneManager;

public class LibraryController {

    @FXML
    private Button dashboardButton;

    @FXML
    private Button libraryButton;

    @FXML
    private Button addMovieButton;

    @FXML
    private Button reviewButton;

    @FXML
    private Button reportsButton;

    @FXML
    private Button logoutButton;

    @FXML
    private TextField searchTextField;

    // Button Actions
    @FXML
    void dashboardScreen(ActionEvent event) {
        SceneManager.switchTo("dashboard.fxml");
    }

    @FXML
    void libraryScreen(ActionEvent event) {
        SceneManager.switchTo("library.fxml");
    }

    @FXML
    void addMovieScreen(ActionEvent event) {
        SceneManager.switchTo("addamovie.fxml");
    }

    @FXML
    void reviewsScreen(ActionEvent event) {
        // You will create reviews.fxml later
        SceneManager.switchTo("reviews.fxml");
    }

    @FXML
    void reportsScreen(ActionEvent event) {
        // You will create reports.fxml later
        SceneManager.switchTo("reports.fxml");
    }

    @FXML
    void logout(ActionEvent event) {
        SceneManager.switchTo("login.fxml");
    }

    // Search Action
    @FXML
    void searchMovies(KeyEvent event) {
        System.out.println("Searching for: " + searchTextField.getText());
        // (Later you will add real search functionality here)
    }
}
