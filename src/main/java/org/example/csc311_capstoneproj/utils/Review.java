package org.example.csc311_capstoneproj.utils;

public class Review {
    private final String movieId;
    private String text;
    private int rating; // 1–5

    public String getMovieId() {
        return movieId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Review(String movieId, String text, int rating) {
        this.movieId = movieId;
        this.text = text;
        this.rating = rating;
    }


}
