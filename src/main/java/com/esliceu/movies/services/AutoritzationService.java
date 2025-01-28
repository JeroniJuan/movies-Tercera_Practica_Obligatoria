package com.esliceu.movies.services;

import com.esliceu.movies.models.Autoritzation;
import com.esliceu.movies.repos.AutoritzationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutoritzationService {
    @Autowired
    private AutoritzationRepo autoritzationRepo;

    public void save(Autoritzation autoritzation) {
        autoritzationRepo.save(autoritzation);
    }
}
