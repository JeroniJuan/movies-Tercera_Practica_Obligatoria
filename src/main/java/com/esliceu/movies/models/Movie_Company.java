package com.esliceu.movies.models;

import jakarta.persistence.*;

@Entity
public class Movie_Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "movie_id", referencedColumnName = "movie_id")
    private Movie movie_id;

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "company_id")
    private Production_Company company_id;

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

    public Production_Company getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Production_Company company_id) {
        this.company_id = company_id;
    }
}
