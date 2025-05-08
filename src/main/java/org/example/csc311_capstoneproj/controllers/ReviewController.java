package org.example.csc311_capstoneproj.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import info.movito.themoviedbapi.model.core.Movie;

public class ReviewController {

    @FXML private TextField movieSearchField;
    @FXML private ToggleButton star1;
    @FXML private ToggleButton star2;
    @FXML private ToggleButton star3;
    @FXML private ToggleButton star4;
    @FXML private ToggleButton star5;
    @FXML private TextArea reviewTextArea;
    @FXML private Button cancelButton;
    @FXML private Button applyButton;

    // the movie currently being reviewed
    private Movie movieForReview;

    // current rating (1–5)
    private int rating = 0;

    // Called by FXMLLoader once the @FXML fields are injected
    @FXML
    public void initialize() {
        // any startup logic—e.g. wire up autocomplete on movieSearchField
    }

    /**
     * Called by SceneManager to pass in the Movie to be reviewed.
     */
    public void setMovieForReview(Movie movie) {
        this.movieForReview = movie;
        // display the movie title in the search field
        movieSearchField.setText(movie.getTitle());
    }

    // Handles clicks on any of the five stars
    @FXML
    private void onStarClicked(ActionEvent event) {
        ToggleButton clicked = (ToggleButton) event.getSource();

        // determine new rating based on which ToggleButton was clicked
        if (clicked == star1) rating = star1.isSelected() ? 1 : 0;
        else if (clicked == star2) rating = star2.isSelected() ? 2 : 1;
        else if (clicked == star3) rating = star3.isSelected() ? 3 : 2;
        else if (clicked == star4) rating = star4.isSelected() ? 4 : 3;
        else if (clicked == star5) rating = star5.isSelected() ? 5 : 4;

        // update the star visuals
        updateStarDisplay();
    }

    // Reset all stars to match the current `rating`
    private void updateStarDisplay() {
        ToggleButton[] stars = { star1, star2, star3, star4, star5 };
        for (int i = 0; i < stars.length; i++) {
            stars[i].setText(i < rating ? "★" : "☆");
            stars[i].setSelected(i < rating);
        }
    }

    // User hits “Cancel”: clear the form (or navigate away)
    @FXML
    private void handleCancel(ActionEvent event) {
        movieSearchField.clear();
        reviewTextArea.clear();
        rating = 0;
        updateStarDisplay();
        // optionally: switch back to the Library scene
    }

    // User hits “Apply”: grab the data and save it
    @FXML
    private void handleSubmit(ActionEvent event) {
        String movie = movieSearchField.getText().trim();
        String review = reviewTextArea.getText().trim();

        if (movie.isEmpty()) {
            // you could show an alert here: “Please enter a movie”
            return;
        }

        // TODO: actually persist this review+rating (e.g. call your model/service)
        System.out.printf("Saving review — Movie: %s | Rating: %d | Text: %s%n",
                movie, rating, review);

        // Then go back to your Library view, or clear the form:
        handleCancel(event);
    }
}