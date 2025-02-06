package com.esliceu.movies.services;

import com.esliceu.movies.models.Autoritzation;
import com.esliceu.movies.models.Permission;
import com.esliceu.movies.repos.AutoritzationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutoritzationService {
    @Autowired
    private AutoritzationRepo autoritzationRepo;

    public void save(Autoritzation autoritzation) {
        autoritzationRepo.save(autoritzation);
    }

    public Autoritzation findById(int requestId) {
        return autoritzationRepo.findById(requestId).orElse(null);
    }

    public List<Autoritzation> findPendingRequests() {
        return autoritzationRepo.findByState(Autoritzation.State.PENDING);
    }

    public List<Autoritzation> findByUserId(int userId) {
        return autoritzationRepo.findByUserId(userId);
    }
}
