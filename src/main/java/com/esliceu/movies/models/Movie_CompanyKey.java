package com.esliceu.movies.models;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class Movie_CompanyKey implements Serializable {
    private int movie_id;
    private int company_id;

    public Movie_CompanyKey(int movieId, int companyId) {
    }

    public Movie_CompanyKey() {
    }

    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }
}
