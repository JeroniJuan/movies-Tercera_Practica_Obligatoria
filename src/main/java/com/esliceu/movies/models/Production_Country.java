package com.esliceu.movies.models;

import jakarta.persistence.*;

@Entity
public class Production_Country {
    @EmbeddedId
    private Production_CountryKey id;

    @ManyToOne
    @MapsId("movie_id")
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne
    @MapsId("country_id")
    @JoinColumn(name = "country_id")
    private Country country;

    public Production_CountryKey getId() {
        return id;
    }

    public void setId(Production_CountryKey id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
