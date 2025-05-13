package org.example.csc311_capstoneproj.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
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

    @FXML
    void loginButtonClicked(ActionEvent event) {
        String email = emailTextField.getText();
        String password = passwordTextField.getText();

        if (validateLogin(email, password)) {
            System.out.println("✅ Login successful");
            SceneManager.switchTo("dashboard.fxml");
        } else {
            System.out.println("❌ Login failed: incorrect email or password.");
        }
    }


    private boolean validateLogin(String email, String password) {
        String query = "SELECT * FROM users WHERE email = ? AND password = ?";

        try {
            Properties props = new Properties();
            InputStream input = getClass().getClassLoader().getResourceAsStream("dbconfig.properties");
            props.load(input);

            String url = props.getProperty("db.url");
            String dbUser = props.getProperty("db.username");
            String dbPass = props.getProperty("db.password");

            Connection conn = DriverManager.getConnection(url, dbUser, dbPass);
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            boolean success = rs.next(); // true if a match is found

            rs.close();
            stmt.close();
            conn.close();

            return success;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
