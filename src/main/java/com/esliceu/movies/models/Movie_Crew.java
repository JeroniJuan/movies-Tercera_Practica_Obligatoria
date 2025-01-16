package com.esliceu.movies.models;

import jakarta.persistence.*;

@Entity
public class Movie_Crew {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    private Person person_id;

    @ManyToOne
    @JoinColumn(name = "movie_id", referencedColumnName = "movie_id")
    private Movie movie_id;

    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "department_id")
    private Department department_id;

    @Column(length = 200)
    private String job;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Person person_id) {
        this.person_id = person_id;
    }

    public Movie getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(Movie movie_id) {
        this.movie_id = movie_id;
    }

    public Department getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(Department department_id) {
        this.department_id = department_id;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
