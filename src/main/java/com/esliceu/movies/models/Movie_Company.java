package com.esliceu.movies.models;

import jakarta.persistence.*;

@Entity
public class Movie_Company {
    @EmbeddedId
    private Movie_CompanyKey id;

    @ManyToOne
    @MapsId("movie_id")
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne
    @MapsId("company_id")
    @JoinColumn(name = "company_id")
    private Production_Company company_id;

    public Movie_CompanyKey getId() {
        return id;
    }

    public void setId(Movie_CompanyKey id) {
        this.id = id;
    }

    public Production_Company getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Production_Company company_id) {
        this.company_id = company_id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie_id) {
        this.movie = movie_id;
    }

    public Production_Company getCompany() {
        return company_id;
    }

    public void setCompany(Production_Company company_id) {
        this.company_id = company_id;
    }
}
