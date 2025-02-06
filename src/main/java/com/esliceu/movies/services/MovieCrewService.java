package com.esliceu.movies.services;

import com.esliceu.movies.models.Movie;
import com.esliceu.movies.models.Movie_Crew;
import com.esliceu.movies.models.Movie_CrewKey;
import com.esliceu.movies.models.Person;
import com.esliceu.movies.repos.MovieCrewRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieCrewService {
    @Autowired
    MovieCrewRepo movieCrewRepo;

    public List<Movie_Crew> findMovieCrewByPersonId(int personId){
        return movieCrewRepo.findByPersonId(personId);
    }

    public List<Movie_Crew> findByMovieId(int movieId) {
        return movieCrewRepo.findByMovieId(movieId);
    }

    public List<Movie_Crew> findAll() {
        return movieCrewRepo.findAll();
    }

    @Transactional
    public void deleteByMovieId(int id) {
        movieCrewRepo.deleteByMovie_Id(id);
    }

    @Transactional
    public void deleteByPersonId(int id) {
        movieCrewRepo.deleteByPersonId(id);
    }
}
