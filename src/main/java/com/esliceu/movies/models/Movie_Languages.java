package com.esliceu.movies.models;

import jakarta.persistence.*;

@Entity
public class Movie_Languages {
    @EmbeddedId
    private Movie_LanguagesKey id;

    @ManyToOne
    @MapsId("movie_id")
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne
    @MapsId("language_id")
    @JoinColumn(name = "language_id")
    private Language language;

    @ManyToOne
    @MapsId("language_role_id")
    @JoinColumn(name = "language_role_id")
    private Language_Role languageRole;

    public Movie_Languages(Movie_LanguagesKey movieLanguagesKey) {
    }

    public Movie_Languages() {
    }

    public Movie_LanguagesKey getId() {
        return id;
    }

    public void setId(Movie_LanguagesKey id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Language_Role getLanguageRole() {
        return languageRole;
    }

    public void setLanguageRole(Language_Role languageRole) {
        this.languageRole = languageRole;
    }
}
