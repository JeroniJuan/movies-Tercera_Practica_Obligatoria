package com.esliceu.movies.services;

import com.esliceu.movies.models.Movie;
import com.esliceu.movies.repos.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    MovieRepo movieRepo;

    public List<Movie> getAllMovies(){
        return movieRepo.findAll();
    }

    public List<Movie> findMovieByTitle(String title){
        return movieRepo.findByTitle(title);
    }
}
