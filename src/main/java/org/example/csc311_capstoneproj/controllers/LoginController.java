package org.example.csc311_capstoneproj.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.example.csc311_capstoneproj.utils.SceneManager;
import org.example.csc311_capstoneproj.models.User;
import org.example.csc311_capstoneproj.controllers.SignupController;

public class LoginController {

    @FXML
    private TextField emailTextField;

    @FXML
    private Button lgnButton;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Label signUpLabel;

    @FXML
    private Label errorLabel;

    @FXML
    void goToSignUp(MouseEvent event) {
        SceneManager.switchTo("signup.fxml");
    }

    @FXML
    void login(ActionEvent event) {
        String enteredEmail = emailTextField.getText();
        String enteredPassword = passwordTextField.getText();

        User signedUpUser = SignupController.getSignedUpUser(); // Get the signed-up user

        if (signedUpUser != null && enteredEmail.equals(signedUpUser.getEmail()) && enteredPassword.equals(signedUpUser.getPassword())) {
            // Successful login
            System.out.println("Login successful!");
            errorLabel.setText("");  // Clear any previous error
            SceneManager.switchTo("dashboard.fxml");
        } else {
            // Invalid login
            System.out.println("Invalid login attempt.");
            errorLabel.setText("Invalid email or password.");
        }
    }

}
