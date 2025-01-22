package com.esliceu.movies.models;

import jakarta.persistence.*;

@Entity
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id")
    private int id;

    @Column(length = 10)
    private String country_iso_code;

    @Column(length = 200)
    private String country_name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry_iso_code() {
        return country_iso_code;
    }

    public void setCountry_iso_code(String country_iso_code) {
        this.country_iso_code = country_iso_code;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }
}
