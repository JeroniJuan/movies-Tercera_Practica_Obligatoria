package com.esliceu.movies.models;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
@Embeddable
public class Movie_KeywordsKey implements Serializable {
    private int movie_id;
    private int keyword_id;

    public Movie_KeywordsKey(int movieId, Integer keywordId) {
    }

    public Movie_KeywordsKey() {
    }

    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    public int getKeyword_id() {
        return keyword_id;
    }

    public void setKeyword_id(int keyword_id) {
        this.keyword_id = keyword_id;
    }
}
