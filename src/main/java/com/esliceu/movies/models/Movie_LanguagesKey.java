package com.esliceu.movies.models;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class Movie_LanguagesKey implements Serializable {
    private int movie_id;
    private int language_id;
    private int language_role_id;

    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    public int getLanguage_id() {
        return language_id;
    }

    public void setLanguage_id(int language_id) {
        this.language_id = language_id;
    }

    public int getLanguage_role_id() {
        return language_role_id;
    }

    public void setLanguage_role_id(int language_role_id) {
        this.language_role_id = language_role_id;
    }
}
