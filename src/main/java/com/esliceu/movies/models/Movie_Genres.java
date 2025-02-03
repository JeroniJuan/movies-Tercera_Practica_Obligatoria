package com.esliceu.movies.models;

import jakarta.persistence.*;

@Entity
public class Movie_Genres {
    @EmbeddedId
    private Movie_GenresKey id;

    @ManyToOne
    @MapsId("movie_id")
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne
    @MapsId("genre_id")
    @JoinColumn(name = "genre_id")
    private Genre genre;

    public Movie_Genres(Movie_GenresKey movieGenresKey) {
    }

    public Movie_Genres() {
    }

    public Movie_GenresKey getId() {
        return id;
    }

    public void setId(Movie_GenresKey id) {
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
