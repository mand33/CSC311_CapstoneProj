package org.example.csc311_capstoneproj.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SignupController {

    @FXML
    private Label dobMsg;

    @FXML
    private TextField dobTextField;

    @FXML
    private Label emailMsg;

    @FXML
    private TextField emailTextField;

    @FXML
    private Label passwordMsg;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Button signUpButton;

    @FXML
    private Label usernameMsg;

    @FXML
    private TextField usernameTextField;

    private boolean isUsernameValid = false;
    private boolean isEmailValid = false;
    private boolean isDobValid = false;
    private boolean isPasswordValid = false;

    String usernameRegex = "^[A-Za-z]{2,25}$";
    String emailRegex = "^[A-Za-z0-9._%+-]+@farmingdale\\.edu$";
    String dobRegex = "^(0[1-9]|1[0-2])/([0-2][0-9]|3[01])/\\d{4}$";
    // TODO passwordregex

    public void initialize() {
        signUpButton.setDisable(true);
        // TODO load stylesheet

        // Username
        usernameTextField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal) { // focus lost
                if (usernameTextField.getText().matches(usernameRegex)) {
                    usernameTextField.getStyleClass().removeAll("invalid");
                    if (!usernameTextField.getStyleClass().contains("valid")) {
                        usernameTextField.getStyleClass().add("valid");
                    }
                    isUsernameValid = true;
                    usernameMsg.setText("✓");
                } else {
                    usernameTextField.getStyleClass().removeAll("valid");
                    if (!usernameTextField.getStyleClass().contains("invalid")) {
                        usernameTextField.getStyleClass().add("invalid");
                    }
                    isUsernameValid = false;
                    usernameMsg.setText("2–25 letters only");
                }
                // TODO checkAllValid;
            }
        });

        // Email
        emailTextField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal) {
                if (emailTextField.getText().matches(emailRegex)) {
                    emailTextField.getStyleClass().removeAll("invalid");
                    if (!emailTextField.getStyleClass().contains("valid")) {
                        emailTextField.getStyleClass().add("valid");
                    }
                    isEmailValid = true;
                    emailMsg.setText("✓");
                } else {
                    emailTextField.getStyleClass().removeAll("valid");
                    if (!emailTextField.getStyleClass().contains("invalid")) {
                        emailTextField.getStyleClass().add("invalid");
                    }
                    isEmailValid = false;
                    emailMsg.setText("Must be @farmingdale.edu");
                }
//                checkAllValid;
            }
        });

        // Date of Birth
        dobTextField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal) {
                if (dobTextField.getText().matches(dobRegex)) {
                    dobTextField.getStyleClass().removeAll("invalid");
                    if (!dobTextField.getStyleClass().contains("valid")) {
                        dobTextField.getStyleClass().add("valid");
                    }
                    isDobValid = true;
                    dobMsg.setText("✓");
                } else {
                    dobTextField.getStyleClass().removeAll("valid");
                    if (!dobTextField.getStyleClass().contains("invalid")) {
                        dobTextField.getStyleClass().add("invalid");
                    }
                    isDobValid = false;
                    dobMsg.setText("Must be MM/DD/YYYY format");
                }
//                checkAllValid;
            }
        });
    }


    @FXML
    void signUp(ActionEvent event) {

    }

}
