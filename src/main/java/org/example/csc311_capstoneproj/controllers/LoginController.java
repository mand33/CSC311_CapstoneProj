package org.example.csc311_capstoneproj.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.example.csc311_capstoneproj.utils.SceneManager;

public class LoginController {

    @FXML
    private TextField emailTextField;

    @FXML
    private Button lgnButton;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Label signUpLabel;

    @FXML
    void goToSignUp(MouseEvent event) {
        SceneManager.switchTo("signup.fxml");
    }

}
