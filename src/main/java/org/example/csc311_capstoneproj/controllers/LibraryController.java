package org.example.csc311_capstoneproj.controllers;

import info.movito.themoviedbapi.model.core.Movie;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.example.csc311_capstoneproj.utils.SavedMoviesManager;
import org.example.csc311_capstoneproj.utils.SceneManager;

import java.util.List;
import java.util.stream.Collectors;

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
    void reviewsScreen(ActionEvent event) {
        SceneManager.switchTo("review.fxml");
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


    @FXML
    void searchMovies(KeyEvent event) {
        String query = searchTextField.getText().toLowerCase();

        List<Movie> saved = SavedMoviesManager.getInstance().getSavedMovies();

        List<Movie> filtered = saved.stream()
                .filter(movie -> movie.getTitle().toLowerCase().contains(query))
                .collect(Collectors.toList());

        libTilePane.getChildren().clear();
        for (Movie movie : filtered) {
            Node movieCard = createMovieCard(movie);
            libTilePane.getChildren().add(movieCard);
        }

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
        Button reviewButton = new Button("Review");
        reviewButton.setStyle("-fx-background-color: #ae6262; -fx-text-fill: #222430; -fx-cursor: hand; -fx-font-family: Segoe UI Light");
        reviewButton.setOnAction(e -> {
            SceneManager.switchToWithController("review.fxml", (ReviewController controller) -> {
                controller.setMovieForReview(movie);  // passing the selected movie
            });

        });

        VBox contentBox = new VBox(10);
        contentBox.setAlignment(Pos.TOP_CENTER);
        contentBox.setPadding(new Insets(10));
        contentBox.setStyle("-fx-background-color: #222430; -fx-background-radius: 10; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.4), 10, 0, 0, 4);");

        // Poster
        ImageView poster = new ImageView();
        poster.setFitHeight(120);
        poster.setFitWidth(80);
        if (movie.getPosterPath() != null) {
            String imageUrl = "https://image.tmdb.org/t/p/w185" + movie.getPosterPath();
            poster.setImage(new Image(imageUrl, true));
        }

        // Title and release date
        Label title = new Label(movie.getTitle());
        title.setStyle("-fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;");
        Label date = new Label(movie.getReleaseDate());
        date.setStyle("-fx-text-fill: #cccccc;");
        VBox textBox = new VBox(5, title, date);
        textBox.setAlignment(Pos.CENTER);

        contentBox.getChildren().addAll(poster, textBox, reviewButton);

        // Overview overlay
        Label overview = new Label(movie.getOverview());
        overview.setWrapText(true);
        overview.setStyle("-fx-background-color:  #222430; -fx-text-fill: white; -fx-padding: 10; -fx-background-radius: 8; -fx-font-size: 10px");
        overview.setVisible(false);
        overview.setOpacity(0);
        overview.setMaxWidth(120);
        overview.setMaxHeight(180);

        // Layer content using StackPane
        StackPane stack = new StackPane(contentBox, overview);
        stack.setPrefSize(140, 220);
        stack.setAlignment(Pos.TOP_CENTER);
        stack.setStyle("-fx-background-color: #222430; -fx-background-radius: 10; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.4), 10, 0, 0, 4);");

        FadeTransition fadeOutContent = new FadeTransition(Duration.millis(200), contentBox);
        fadeOutContent.setToValue(0);

        FadeTransition fadeInOverview = new FadeTransition(Duration.millis(200), overview);
        fadeInOverview.setToValue(1);

        FadeTransition fadeInContent = new FadeTransition(Duration.millis(200), contentBox);
        fadeInContent.setToValue(1);

        FadeTransition fadeOutOverview = new FadeTransition(Duration.millis(200), overview);
        fadeOutOverview.setToValue(0);

        stack.setOnMouseClicked(e -> {
            overview.setVisible(true);
            fadeOutContent.playFromStart();
            fadeInOverview.playFromStart();
            fadeOutContent.setOnFinished(evt -> contentBox.setVisible(false));
        });

        stack.setOnMouseExited(e -> {
            contentBox.setVisible(true);
            fadeOutOverview.playFromStart();
            fadeInContent.playFromStart();
            fadeOutOverview.setOnFinished(evt -> overview.setVisible(false));
        });

        VBox outerCard = new VBox(stack);
        outerCard.setPadding(new Insets(10));
        return outerCard;
    }
}
