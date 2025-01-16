package com.esliceu.movies.models;

import jakarta.persistence.*;

@Entity
public class Movie_Cast {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "movie_id", referencedColumnName = "movie_id")
    private Movie movie_id;

    @ManyToOne
    @JoinColumn(name = "gender_id", referencedColumnName = "gender_id")
    private Gender gender_id;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    private Person person_id;

    @Column(length = 400)
    private String character_name;

    private int cast_order;

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

    public Gender getGender_id() {
        return gender_id;
    }

    public void setGender_id(Gender gender_id) {
        this.gender_id = gender_id;
    }

    public Person getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Person person_id) {
        this.person_id = person_id;
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
