package com.esliceu.movies.services;

import com.esliceu.movies.models.Gender;
import com.esliceu.movies.repos.GenderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenderService {
    @Autowired
    private GenderRepo genderRepo;

    public List<Gender> findAll() {
        return genderRepo.findAll();
    }

    public Gender findById(int id) {
        return genderRepo.findById(id).orElse(null);
    }

    public Gender save(Gender gender) {
        return genderRepo.save(gender);
    }

    public void deleteById(int id) {
        genderRepo.deleteById(id);
    }
}
