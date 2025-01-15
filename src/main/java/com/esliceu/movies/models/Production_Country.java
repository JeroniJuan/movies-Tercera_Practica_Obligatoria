package com.esliceu.movies.models;

import jakarta.persistence.*;

@Entity
public class Production_Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(referencedColumnName = "movie_id")
    private int movie_id;

    @ManyToOne
    @JoinColumn(referencedColumnName = "country_id")
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
