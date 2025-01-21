package com.esliceu.movies.models;

import jakarta.persistence.*;

@Entity
public class Movie_Cast {
    @EmbeddedId
    private Movie_CastKey id;

    @ManyToOne
    @MapsId("movie_id")
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne
    @MapsId("gender_id")
    @JoinColumn(name = "gender_id")
    private Gender gender;

    @ManyToOne
    @MapsId("person_id")
    @JoinColumn(name = "person_id")
    private Person person;

    @Column(length = 400)
    private String character_name;

    @Column
    private int cast_order;

    public Movie_CastKey getId() {
        return id;
    }

    public void setId(Movie_CastKey id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getCharacter_name() {
        return character_name;
    }

    public void setCharacter_name(String character_name) {
        this.character_name = character_name;
    }

    public int getCast_order() {
        return cast_order;
    }

    public void setCast_order(int cast_order) {
        this.cast_order = cast_order;
    }
}
