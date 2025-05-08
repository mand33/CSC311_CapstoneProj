package org.example.csc311_capstoneproj.controllers;

import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbSearch;
import info.movito.themoviedbapi.model.core.Movie;
import info.movito.themoviedbapi.model.core.MovieResultsPage;
import info.movito.themoviedbapi.tools.TmdbHttpClient;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.example.csc311_capstoneproj.utils.SceneManager;

import java.util.List;

public class AddAMovieController {

    @FXML
    private Button backtoDashButton;

    @FXML
    private Button libraryButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Button reviewButton;

    @FXML
    private TextField searchTextField;

    @FXML
    private ScrollPane searchScrollPane;

    @FXML
    private VBox searchVBox;


    @FXML
    void backToDash(ActionEvent event) {
        SceneManager.switchTo("dashboard.fxml");

    }

    @FXML
    void libraryScreen(ActionEvent event) {

    }

    @FXML
    void logout(ActionEvent event) {
        SceneManager.switchTo("login.fxml");

    }

    @FXML
    void reviewsScreen(ActionEvent event) {

    }

    @FXML
    void searchMovies(KeyEvent event) {
        String API_KEY = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0OThmZjIzYWFhMWUzMTA1MzQwMjA4ZDg1ZjI2MDYyZiIsIm5iZiI6MTc0NjU3ODE1NC40MzYsInN1YiI6IjY4MWFhYWVhZDgwOWI3MGE0YzlmMDMyMyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.qA6P7X9t1GYqB17rSDXha-GuQ0ugFnmkdMZt9aDIrl4";
        TmdbHttpClient httpClient = new TmdbHttpClient(API_KEY);
        TmdbApi tmdbApi = new TmdbApi(httpClient);
        TmdbSearch searchClient = new TmdbSearch(tmdbApi);

        String userQuery = searchTextField.getText();

        if (userQuery == null || userQuery.isBlank()) return;

        // New thread for search to not disrupt UI
        Task<List<Movie>> searchTask = new Task<>() {

            @Override
            protected List<Movie> call() throws Exception {
                MovieResultsPage page = searchClient.searchMovie(
                        userQuery + " ", false, "en-US", null, 1, null, null);
                return page.getResults();
            }
        };

        searchTask.setOnSucceeded(e -> {
            List<Movie> results = searchTask.getValue();
            searchVBox.getChildren().clear();

            for (Movie movie : results) {
                searchVBox.getChildren().add(createMovieCard(movie));
            }
        });

        searchTask.setOnFailed(e -> {
            Throwable exception = searchTask.getException();
            exception.printStackTrace();
        });

        new Thread(searchTask).start();
    }

    private HBox createMovieCard(Movie movie) {
        HBox card = new HBox(10);
        card.setPadding(new Insets(10));
        card.setStyle("-fx-background-color: #2b2b2b; -fx-background-radius: 8;");
        card.setAlignment(Pos.CENTER_LEFT);
        card.maxWidthProperty().bind(searchVBox.widthProperty());

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
        Label date = new Label("Release Date: " + movie.getReleaseDate());
        date.setStyle("-fx-text-fill: #cccccc;");
        textBox.getChildren().addAll(title, date);

        card.getChildren().addAll(poster, textBox);
        return card;
    }

    public void initialize() {
        Platform.runLater(() -> {
            searchScrollPane.getScene().getStylesheets().add(getClass().getResource("/org/example/csc311_capstoneproj/fxml/css/scrollpane.css").toExternalForm());
        });
    }
}
