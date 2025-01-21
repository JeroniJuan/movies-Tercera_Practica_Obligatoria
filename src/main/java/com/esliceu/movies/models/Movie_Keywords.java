package com.esliceu.movies.models;

import jakarta.persistence.*;

@Entity
public class Movie_Keywords {
    @EmbeddedId
    private Movie_KeywordsKey id;

    @ManyToOne
    @MapsId("movie_id")
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne
    @MapsId("keyword_id")
    @JoinColumn(name = "keyword_id")
    private Keyword keyword;

    public Movie_KeywordsKey getId() {
        return id;
    }

    public void setId(Movie_KeywordsKey id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Keyword getKeyword() {
        return keyword;
    }

    public void setKeyword(Keyword keyword) {
        this.keyword = keyword;
    }
}
