package com.esliceu.movies.models;

import jakarta.persistence.*;

@Entity
public class Keyword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "keyword_id")
    private int id;

    @Column(length = 100)
    private String keyword_name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKeyword_name() {
        return keyword_name;
    }

    public void setKeyword_name(String keyword_name) {
        this.keyword_name = keyword_name;
    }
}
