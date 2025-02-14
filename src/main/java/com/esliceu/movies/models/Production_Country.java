package com.esliceu.movies.models;

import jakarta.persistence.*;

@Entity
public class Production_Country {
    @EmbeddedId
    private Production_CountryKey id;

    @ManyToOne
    @MapsId("movieId")
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne
    @MapsId("countryId")
    @JoinColumn(name = "country_id")
    private Country country;

    public Production_Country(Production_CountryKey productionCountryKey) {
    }

    public Production_Country() {
    }

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
