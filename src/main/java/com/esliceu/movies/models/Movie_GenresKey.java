package com.esliceu.movies.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class Movie_GenresKey implements Serializable {
    @Column(name = "movie_id")
    private int movieId;

    @Column(name = "genre_id")
    private int genreId;

    public Movie_GenresKey(int movieId, int genreId) {
        this.movieId = movieId;
        this.genreId = genreId;
    }

    public Movie_GenresKey() {
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }
}

