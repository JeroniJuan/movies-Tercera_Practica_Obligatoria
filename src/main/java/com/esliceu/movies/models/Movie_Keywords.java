package com.esliceu.movies.models;

import jakarta.persistence.*;

@Entity
public class Movie_Keywords {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "movie_id", referencedColumnName = "movie_id")
    private Movie movie_id;

    @ManyToOne
    @JoinColumn(name = "keyword_id", referencedColumnName = "keyword_id")
    private Keyword keyword_id;

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

    public Keyword getKeyword_id() {
        return keyword_id;
    }

    public void setKeyword_id(Keyword keyword_id) {
        this.keyword_id = keyword_id;
    }
}
