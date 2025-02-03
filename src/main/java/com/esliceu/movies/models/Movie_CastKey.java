package com.esliceu.movies.models;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class Movie_CastKey implements Serializable {
    private int movie_id;
    private int person_id;
    private int gender_id;

    public Movie_CastKey(int movieId, int id) {
    }

    public Movie_CastKey() {
    }

    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public int getGender_id() {
        return gender_id;
    }

    public void setGender_id(int gender_id) {
        this.gender_id = gender_id;
    }
}
