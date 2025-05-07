package org.example.csc311_capstoneproj.controllers;

import com.sun.net.httpserver.Request;
import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbSearch;
import info.movito.themoviedbapi.model.core.Movie;
import info.movito.themoviedbapi.model.core.MovieResultsPage;
import info.movito.themoviedbapi.tools.TmdbException;
import info.movito.themoviedbapi.tools.TmdbHttpClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import org.example.csc311_capstoneproj.utils.SceneManager;

public class AddAMovieController {

    private final String API_KEY = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0OThmZjIzYWFhMWUzMTA1MzQwMjA4ZDg1ZjI2MDYyZiIsIm5iZiI6MTc0NjU3ODE1NC40MzYsInN1YiI6IjY4MWFhYWVhZDgwOWI3MGE0YzlmMDMyMyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.qA6P7X9t1GYqB17rSDXha-GuQ0ugFnmkdMZt9aDIrl4";

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
        TmdbHttpClient httpClient = new TmdbHttpClient(API_KEY);
        TmdbApi tmdbApi = new TmdbApi(httpClient);

        TmdbSearch searchClient = new TmdbSearch(tmdbApi);

        try {
            MovieResultsPage results = searchClient.searchMovie(
                    "Dune",         // query
                    false,          // includeAdult
                    "en-US",        // language
                    null,           // primaryReleaseYear
                    1,              // page
                    "US",           // region
                    null            // year
            );

            for (Movie movie : results.getResults()) {
                System.out.println(movie.getTitle() + " (" + movie.getReleaseDate() + ")");
            }

        } catch (TmdbException e) {
            e.printStackTrace();
        }
    }





}
