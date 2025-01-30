package com.esliceu.movies.services;

import com.esliceu.movies.models.Language_Role;
import com.esliceu.movies.repos.LanguageRoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageRoleService {
    private final LanguageRoleRepo languageRoleRepo;

    @Autowired
    public LanguageRoleService(LanguageRoleRepo languageRoleRepo) {
        this.languageRoleRepo = languageRoleRepo;
    }

    public List<Language_Role> findAll() {
        return languageRoleRepo.findAll();
    }

    public Language_Role findById(int id) {
        return languageRoleRepo.findById(id);
    }

    public Language_Role save(Language_Role languageRole) {
        return languageRoleRepo.save(languageRole);
    }

    public void deleteById(int id) {
        languageRoleRepo.deleteById(id);
    }

    public Language_Role findByName(String roleName) {
        return languageRoleRepo.findByLanguageRole(roleName);
    }
}
