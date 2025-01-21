package com.esliceu.movies.models;

import jakarta.persistence.*;

@Entity
public class Movie_Genre {
    @EmbeddedId
    private Movie_GenreKey id;

    @ManyToOne
    @MapsId("movie_id")
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne
    @MapsId("genre_id")
    @JoinColumn(name = "genre_id")
    private Genre genre;

    public Movie_GenreKey getId() {
        return id;
    }

    public void setId(Movie_GenreKey id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
