package com.esliceu.movies.models;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class Movie_GenreKey implements Serializable {
    private int movie_id;
    private int genre_id;

    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    public int getGenre_id() {
        return genre_id;
    }

    public void setGenre_id(int genre_id) {
        this.genre_id = genre_id;
    }
}
