package com.esliceu.movies.services;

import com.esliceu.movies.models.Language;
import com.esliceu.movies.repos.LanguageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageService {
    private final LanguageRepo languageRepo;

    @Autowired
    public LanguageService(LanguageRepo languageRepo) {
        this.languageRepo = languageRepo;
    }

    public List<Language> findAll() {
        return languageRepo.findAll();
    }

    public Language findById(int id) {
        return languageRepo.findById(id);
    }

    public Language save(Language language) {
        return languageRepo.save(language);
    }

    public void deleteById(int id) {
        languageRepo.deleteById(id);
    }
}
