package com.esliceu.movies.services;

import com.esliceu.movies.repos.MovieCrewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieCrewService {
    @Autowired
    MovieCrewRepo movieCrewRepo;
    public int getDirectorByName(String directorName) {
        return movieCrewRepo.findByPersonPersonName(directorName).getFirst().getId().getPerson_id();
    }
}
