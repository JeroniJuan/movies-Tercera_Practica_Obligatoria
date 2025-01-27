package com.esliceu.movies.repos;

import com.esliceu.movies.models.Language_Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRoleRepo extends JpaRepository<Language_Role, Integer> {
    Language_Role findById(int id);
}
