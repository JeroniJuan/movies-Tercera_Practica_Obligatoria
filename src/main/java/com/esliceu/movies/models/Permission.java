package com.esliceu.movies.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permission_id")
    private int id;

    public enum permission_name {
        upload_movie, modify_movie, remove_movie
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "permission_name")
    private Permission.permission_name permissionName;

    @OneToMany(mappedBy = "permission")
    private List<Autoritzation> autoritzations;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public permission_name getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(permission_name permissionName) {
        this.permissionName = permissionName;
    }

    public List<Autoritzation> getAutoritzations() {
        return autoritzations;
    }

    public void setAutoritzations(List<Autoritzation> autoritzations) {
        this.autoritzations = autoritzations;
    }
}
