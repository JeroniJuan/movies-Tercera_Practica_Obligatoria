package com.esliceu.movies.models;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class Production_CountryKey implements Serializable {
    private int movie_id;
    private int country_id;

    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    public int getCountry_id() {
        return country_id;
    }

    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }
}
