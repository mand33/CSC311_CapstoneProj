package org.example.csc311_capstoneproj.controllers;

import info.movito.themoviedbapi.model.core.Movie;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import org.example.csc311_capstoneproj.utils.SavedMoviesManager;
import org.example.csc311_capstoneproj.utils.SceneManager;

import java.util.List;

public class LibraryController {

    @FXML
    private Button addAMovieButton;

    @FXML
    private Button backToDash;

    @FXML
    private Button logoutButton;

    @FXML
    private Button reviewButton;

    @FXML
    private ScrollPane searchScrollPane;

    @FXML
    private TextField searchTextField;

    @FXML
    private TilePane libTilePane;

    @FXML
    void addAMovie(ActionEvent event) {
        SceneManager.switchTo("addamovie.fxml");
    }

    @FXML
    void goBackToDash(ActionEvent event) {
        SceneManager.switchTo("dashboard.fxml");
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

    }

    public void initialize() {
        Platform.runLater(() -> {
                searchScrollPane.getScene().getStylesheets().add(getClass().getResource("/org/example/csc311_capstoneproj/fxml/css/scrollpane.css").toExternalForm());
            });
        libTilePane.setPrefColumns(3);
        libTilePane.setTileAlignment(Pos.TOP_LEFT);

        List<Movie> saved = SavedMoviesManager.getInstance().getSavedMovies();
        for (Movie movie : saved) {
            Node movieCard = createMovieCard(movie);
            libTilePane.getChildren().add(movieCard);
        }
        searchScrollPane.setFitToWidth(true);
        searchScrollPane.setContent(libTilePane);
    }

    private VBox createMovieCard(Movie movie) {
        VBox card = new VBox(10);
        card.setPadding(new Insets(10));
        card.setStyle("-fx-background-color: #222430; -fx-background-radius: 10; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.4), 10, 0, 0, 4);");
        card.setPrefWidth(100);
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

}
