package com.esliceu.movies.models;

import jakarta.persistence.*;

@Entity
public class Movie_Crew {
    @EmbeddedId
    private Movie_CrewKey id;

    @ManyToOne
    @MapsId("person_id")
    @JoinColumn(name = "person_id")
    private Person person;

    @ManyToOne
    @MapsId("movie_id")
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne
    @MapsId("department_id")
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(length = 200)
    private String job;

    public Movie_Crew() {
    }

    public Movie_CrewKey getId() {
        return id;
    }

    public void setId(Movie_CrewKey id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
