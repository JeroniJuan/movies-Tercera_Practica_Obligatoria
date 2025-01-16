package com.esliceu.movies.models;

import jakarta.persistence.*;

@Entity
public class Movie_Languages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "movie_id", referencedColumnName = "movie_id")
    private Movie movie_id;

    @ManyToOne
    @JoinColumn(name = "language_id", referencedColumnName = "language_id")
    private Language language_id;

    @ManyToOne
    @JoinColumn(name = "language_role_id", referencedColumnName = "role_id")
    private Language_Role language_role_id;

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

    public Language getLanguage_id() {
        return language_id;
    }

    public void setLanguage_id(Language language_id) {
        this.language_id = language_id;
    }

    public Language_Role getLanguage_role_id() {
        return language_role_id;
    }

    public void setLanguage_role_id(Language_Role language_role_id) {
        this.language_role_id = language_role_id;
    }
}
