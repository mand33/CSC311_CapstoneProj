package org.example.csc311_capstoneproj.controllers;

import info.movito.themoviedbapi.model.core.Movie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.example.csc311_capstoneproj.utils.SavedMoviesManager;
import org.example.csc311_capstoneproj.utils.SceneManager;
import org.example.csc311_capstoneproj.models.Review;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private HBox recentsHBox;

    @FXML
    public BarChart<String, Number> barChart;

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
        SceneManager.switchTo("review.fxml");

    }

    public void initialize() {
        List<Movie> saved = SavedMoviesManager.getInstance().getSavedMovies();
        for (Movie movie : saved) {
            Node movieCard = createMovieCard(movie);
            recentsHBox.getChildren().add(movieCard);
        }
        barChart.getStylesheets().add(getClass().getResource("/org/example/csc311_capstoneproj/fxml/css/barchart.css").toExternalForm());
        populateBarChart();
    }

    private VBox createMovieCard(Movie movie) {
        VBox card = new VBox(10);
        card.setPadding(new Insets(10));
        card.setStyle("-fx-background-color: #222430; -fx-background-radius: 10; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.4), 10, 0, 0, 4);");
        card.setPrefWidth(190);
        card.setPrefHeight(200);

        // Poster Image
        ImageView poster = new ImageView();
        poster.setFitHeight(120);
        poster.setFitWidth(80);

        if (movie.getPosterPath() != null) {
            String imageUrl = "https://image.tmdb.org/t/p/w185" + movie.getPosterPath();
            poster.setImage(new Image(imageUrl, true));
        }

        // Text details
        VBox textBox = new VBox(5);
        Label title = new Label(movie.getTitle());
        title.setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");
        Label date = new Label(movie.getReleaseDate());
        date.setStyle("-fx-text-fill: #cccccc;");

        textBox.getChildren().addAll(title, date);

        card.getChildren().addAll(poster, textBox);
        return card;
    }

    public void populateBarChart() {
        barChart.getData().clear();

        Map<Integer, Integer> ratingCounts = new HashMap<>();
        for (int i = 1; i <= 5; i++) {
            ratingCounts.put(i, 0);
        }

        for (Review review : SavedMoviesManager.getInstance().getAllReviews().values()) {
            int rating = review.getRating();
            ratingCounts.put(rating, ratingCounts.getOrDefault(rating, 0) + 1);
        }

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        for (int i = 1; i <= 5; i++) {
            series.getData().add(new XYChart.Data<>(String.valueOf(i), ratingCounts.get(i)));
        }

        barChart.getData().add(series);
    }

}
