package com.esliceu.movies.models;

import jakarta.persistence.*;

@Entity
public class Language_Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private int id;

    @Column(length = 20)
    private String language_role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLanguage_role() {
        return language_role;
    }

    public void setLanguage_role(String language_role) {
        this.language_role = language_role;
    }
}
