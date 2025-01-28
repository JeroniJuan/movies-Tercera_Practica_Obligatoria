package com.esliceu.movies.services;

import com.esliceu.movies.models.Autoritzation;
import com.esliceu.movies.models.Permission;
import com.esliceu.movies.models.User;
import com.esliceu.movies.repos.UserRepo;
import com.esliceu.movies.utils.StringToSHA256;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;
    
    public List<User> findAll() {
        return userRepo.findAll();
    }

    public User findById(int id) {
        return userRepo.findById(id).orElse(null);
    }

    public User save(User user) throws NoSuchAlgorithmException {
        user.setUserPassword(StringToSHA256.getSHA256(user.getUserPassword()));
        return userRepo.save(user);
    }

    public void deleteById(int id) {
        userRepo.deleteById(id);
    }

    public User findByEmail(String mail) {
        return userRepo.findByMail(mail);
    }

    public User findByUserName(String username) {
        return userRepo.findByUserName(username);
    }

    public boolean checkPassword(User user, String password) throws NoSuchAlgorithmException {
        return StringToSHA256.getSHA256(password).equals(user.getUserPassword());
    }

    public boolean isUserAuthorized(User user, Permission.permission_name permissionName) {
        List<Autoritzation> autoritzations = user.getAutoritzations();

        for (Autoritzation autoritzation : autoritzations) {
            if (autoritzation.getPermission().getPermissionName() == permissionName &&
                    autoritzation.getState() == Autoritzation.State.ACCEPTED) {
                return true;
            }
        }

        return false;
    }
}
