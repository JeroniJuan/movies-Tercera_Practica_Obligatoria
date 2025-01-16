package com.esliceu.movies.models;

import jakarta.persistence.*;

@Entity
public class Production_Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int company_id;

    @Column(length = 200)
    private String company_name;

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }
}
