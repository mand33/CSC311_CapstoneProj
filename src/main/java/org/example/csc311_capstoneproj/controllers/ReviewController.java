package org.example.csc311_capstoneproj.controllers;

import info.movito.themoviedbapi.model.core.Movie;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.example.csc311_capstoneproj.utils.Review;
import org.example.csc311_capstoneproj.utils.SavedMoviesManager;
import org.example.csc311_capstoneproj.utils.SceneManager;

import java.util.Map;

public class ReviewController {

    @FXML
    private Button addAMovieButton;

    @FXML
    private Button backToDashButton;

    @FXML
    private Button libraryButton;

    @FXML
    private Button logoutButton;

    @FXML
    private ComboBox<String> ratingComboBox;

    @FXML
    private TextArea reviewInput;

    @FXML
    private VBox reviewList;

    @FXML
    private ScrollPane reviewScrollPane;

    @FXML
    private Button submitButton;

    private Movie currentMovie;

    @FXML
    void addAMovie(ActionEvent event) {
        SceneManager.switchTo("addamovie.fxml");

    }

    @FXML
    void backToDash(ActionEvent event) {
        SceneManager.switchTo("dashboard.fxml");

    }

    @FXML
    void goToLibrary(ActionEvent event) {
        SceneManager.switchTo("library.fxml");
    }

    @FXML
    void logout(ActionEvent event) {
        SceneManager.switchTo("login.fxml");

    }

    public void setMovieForReview(Movie movie) {
        this.currentMovie = movie;
        reviewInput.setVisible(true);
        ratingComboBox.setVisible(true);
        submitButton.setVisible(true);
    }

    public void initialize() {
        reviewInput.setVisible(false);
        ratingComboBox.setVisible(false);
        submitButton.setVisible(false);

        reviewScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        reviewScrollPane.setFitToWidth(true);

        Platform.runLater(() -> {
                    reviewScrollPane.getScene().getStylesheets().add(getClass().getResource("/org/example/csc311_capstoneproj/fxml/css/scrollpane.css").toExternalForm());
                });

        // Populate star options
        for (int i = 1; i <= 5; i++) {
            ratingComboBox.getItems().add("★".repeat(i) + " (" + i + ")");
        }

        // If not writing a review, load existing reviews
        if (currentMovie == null) {
            loadAllReviews();
        }
    }

    private void loadAllReviews() {
        reviewList.getChildren().clear();
        Map<String, Review> reviews = SavedMoviesManager.getInstance().getAllReviews();

        for (Review review : reviews.values()) {
            // Get the corresponding Movie object
            Movie movie = SavedMoviesManager.getInstance().getSavedMovieById(review.getMovieId());
            if (movie == null) continue;

            VBox reviewBox = new VBox(5);
            reviewBox.setStyle("-fx-background-color: #2a2d3e; -fx-padding: 10; -fx-background-radius: 10;");
            Label title = new Label(movie.getTitle());
            title.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");

            Label rating = new Label("Rating: " + "★".repeat(review.getRating()));
            rating.setStyle("-fx-text-fill: gold;");

            Label text = new Label("Review: " + review.getText());
            text.setWrapText(true);
            text.setStyle("-fx-text-fill: lightgray;");

            ImageView poster = new ImageView();
            poster.setFitHeight(120);
            poster.setFitWidth(80);
            if (movie.getPosterPath() != null) {
                String imageUrl = "https://image.tmdb.org/t/p/w185" + movie.getPosterPath();
                poster.setImage(new Image(imageUrl, true));
            }
            HBox topRow = new HBox(10, poster, title, text);
            reviewBox.getChildren().addAll(topRow, rating);
            reviewList.getChildren().add(reviewBox);
        }
    }

    @FXML
    void submitReview(ActionEvent event) {
        if (currentMovie == null) return;

        String reviewText = reviewInput.getText();
//        int rating = ratingComboBox.getSelectionModel().getSelectedIndex() + 1;
        String selected = ratingComboBox.getValue();
        int rating = Integer.parseInt(selected.replaceAll("[^0-9]", ""));

        if (reviewText.isBlank() || ratingComboBox.getSelectionModel().isEmpty()) return;

        Review review = new Review(String.valueOf(currentMovie.getId()), reviewText, rating);
        SavedMoviesManager.getInstance().addReview(review);

        reviewInput.clear();
        ratingComboBox.getSelectionModel().clearSelection();
        SceneManager.switchTo("dashboard.fxml");

    }

}
