package com.esliceu.movies.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class Production_CountryKey implements Serializable {
    @Column(name = "movie_id")
    private int movieId;

    @Column(name = "country_id")
    private int countryId;

    public Production_CountryKey(int movieId, int countryId) {
        this.movieId = movieId;
        this.countryId = countryId;
    }

    public Production_CountryKey() {
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }
}
