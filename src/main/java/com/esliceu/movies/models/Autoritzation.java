package com.esliceu.movies.models;

import jakarta.persistence.*;

@Entity
public class Autoritzation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "autoritzation_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @JoinColumn(name = "permission_id")
    Permission permission;

    public enum State {
        PENDING, ACCEPTED, REJECTED
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private State state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
