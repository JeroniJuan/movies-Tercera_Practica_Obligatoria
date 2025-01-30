package com.esliceu.movies.repos;

import com.esliceu.movies.models.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepo extends JpaRepository<Language, Integer> {
    Language findById(int id);

    Language findByLanguageName(String languageName);
}
