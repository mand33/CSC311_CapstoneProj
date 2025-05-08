package org.example.csc311_capstoneproj.utils;

import java.util.ArrayList;
import java.util.List;

import info.movito.themoviedbapi.model.core.Movie;

public class SavedMoviesManager {
    private static final SavedMoviesManager instance = new SavedMoviesManager();
    private final List<Movie> savedMovies = new ArrayList<>();

    private SavedMoviesManager() {}

    public static SavedMoviesManager getInstance() {
        return instance;
    }

    public void addMovie(Movie movie) {
        if (!savedMovies.contains(movie)) {
            savedMovies.add(movie);
        }
    }

    public void removeMovie(Movie movie) {
        if (savedMovies.contains(movie)) {
            savedMovies.remove(movie);
        }
    }

    public List<Movie> getSavedMovies() {
        return new ArrayList<>(savedMovies); // Return a copy to avoid mutation
    }
}

