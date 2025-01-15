package com.esliceu.movies.models;

import jakarta.persistence.*;

@Entity
public class Language_Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int role_id;

    @Column(length = 20)
    private String language_role;

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getLanguage_role() {
        return language_role;
    }

    public void setLanguage_role(String language_role) {
        this.language_role = language_role;
    }
}
