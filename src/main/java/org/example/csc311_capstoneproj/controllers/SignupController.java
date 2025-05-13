package org.example.csc311_capstoneproj.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.example.csc311_capstoneproj.models.User;
import org.example.csc311_capstoneproj.utils.SceneManager;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;
import java.util.*;

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

    private static User signedUpUser;

    private boolean isUsernameValid = false;
    private boolean isEmailValid = false;
    private boolean isDobValid = false;
    private boolean isPasswordValid = false;

    String usernameRegex = "^[A-Za-z]{2,25}$";
    String emailRegex = "^[A-Za-z0-9._%+-]+@farmingdale\\.edu$";
    String dobRegex = "^(0[1-9]|1[0-2])/([0-2][0-9]|3[01])/\\d{4}$";
    String passwordRegex = "^.{10,}$";

    public void initialize() {
        signUpButton.setDisable(true);

        Platform.runLater(() -> {
            dobTextField.getScene().getStylesheets().add(getClass().getResource("/org/example/csc311_capstoneproj/fxml/css/style.css").toExternalForm());
        });

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
                checkAllValid();
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
                checkAllValid();
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
                checkAllValid();
            }
        });

        // Password
        passwordTextField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal) { // focus lost
                if (passwordTextField.getText().matches(passwordRegex)) {
                    passwordTextField.getStyleClass().removeAll("invalid");
                    if (!passwordTextField.getStyleClass().contains("valid")) {
                        passwordTextField.getStyleClass().add("valid");
                    }
                    isPasswordValid = true;
                    passwordMsg.setText("✓");
                } else {
                    passwordTextField.getStyleClass().removeAll("valid");
                    if (!passwordTextField.getStyleClass().contains("invalid")) {
                        passwordTextField.getStyleClass().add("invalid");
                    }
                    isPasswordValid = false;
                    passwordMsg.setText("Must have at least 10 characters");
                }
                checkAllValid();
            }
        });
    }

    private void checkAllValid() {
        signUpButton.setDisable(!(isUsernameValid && isEmailValid && isDobValid && isPasswordValid));
    }

    public static User getSignedUpUser() {
        return signedUpUser;
    }

    @FXML
    void signUp(ActionEvent event) {
        String username = usernameTextField.getText();
        String email = emailTextField.getText();
        String dob = dobTextField.getText();
        String password = passwordTextField.getText();

        if (registerUser(username, email, dob, password)) {
            System.out.println("✅ User registered successfully!");
            signedUpUser = new User(username, email, password, dob);
            System.out.println("✅ User registered successfully!" + '\n' + "User signed up: " + username + " | " + email);
            SceneManager.switchTo("dashboard.fxml");
        } else {
            System.out.println("❌ Registration failed. User may already exist or DB error occurred.");
        }
    }


    private boolean registerUser(String username, String email, String dob, String password) {
        String insertSQL = "INSERT INTO users (username, email, dob, password) VALUES (?, ?, ?, ?)";

        try {
            Properties props = new Properties();
            InputStream input = getClass().getClassLoader().getResourceAsStream("dbconfig.properties");
            props.load(input);

            String url = props.getProperty("db.url");
            String dbUser = props.getProperty("db.username");
            String dbPass = props.getProperty("db.password");

            Connection conn = DriverManager.getConnection(url, dbUser, dbPass);
            PreparedStatement stmt = conn.prepareStatement(insertSQL);

            stmt.setString(1, username);
            stmt.setString(2, email);
            stmt.setString(3, dob);
            stmt.setString(4, password);

            int result = stmt.executeUpdate();

            stmt.close();
            conn.close();

            return result > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
