package com.esliceu.movies.services;

import com.esliceu.movies.models.User;
import com.esliceu.movies.models.Permission;
import com.esliceu.movies.models.Autoritzation;

import com.esliceu.movies.repos.PermissionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {

    @Autowired
    private PermissionRepo permissionRepo;
    @Autowired
    UserService userService;

    public boolean isUserAuthorized(int id, Permission.permission_name permissionName) {
        Permission permission = permissionRepo.findByPermissionName(permissionName);
    User user = userService.findById(id);

        if (permission == null) {
            return false;
        }

        List<Autoritzation> autoritzations = user.getAutoritzations();
        for (Autoritzation autoritzation : autoritzations) {
            if (autoritzation.getPermission().equals(permission) && autoritzation.getState() == Autoritzation.State.ACCEPTED) {
                return true;
            }
        }

        return false;
    }

    public List<Permission> findAll() {
        return permissionRepo.findAll();
    }

    public Permission findById(int id) {
        return permissionRepo.findById(id).orElse(null);
    }
}
