package com.esliceu.movies.models;

import jakarta.persistence.*;

@Entity
public class Movie_Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "movie_id", referencedColumnName = "movie_id")
    private Movie movie_id;

    @ManyToOne
    @JoinColumn(name = "genre_id", referencedColumnName = "genre_id")
    private Genre genre_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Movie getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(Movie movie_id) {
        this.movie_id = movie_id;
    }

    public Genre getGenre_id() {
        return genre_id;
    }

    public void setGenre_id(Genre genre_id) {
        this.genre_id = genre_id;
    }
}
