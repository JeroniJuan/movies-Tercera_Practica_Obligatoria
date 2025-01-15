package com.esliceu.movies.models;

import jakarta.persistence.*;

@Entity
public class Gender {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int gender_id;

    @Column(length = 20)
    private String gender;
}
