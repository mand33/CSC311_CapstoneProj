package org.example.csc311_capstoneproj.utils;

import java.util.*;

import info.movito.themoviedbapi.model.core.Movie;

public class SavedMoviesManager {
    private static final SavedMoviesManager instance = new SavedMoviesManager();
    private final List<Movie> savedMovies = new ArrayList<>();
    private final Map<String, Review> reviews = new HashMap<>();

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

    public Movie getSavedMovieById(String movieId) {
        return savedMovies.stream()
                .filter(movie -> String.valueOf(movie.getId()).equals(movieId))
                .findFirst()
                .orElse(null);
    }

    public void addReview(Review review) {
        reviews.put(review.getMovieId(), review);
    }

    public Review getReviewForMovie(String movieId) {
        return reviews.get(movieId);
    }

    public Map<String, Review> getAllReviews() {
        return Collections.unmodifiableMap(reviews);
    }

    public boolean hasReview(String movieId) {
        return reviews.containsKey(movieId);
    }

    public void removeReview(String movieId) {
        reviews.remove(movieId);
    }
}

