package com.esliceu.movies.services;

import com.esliceu.movies.models.Movie_Genres;
import com.esliceu.movies.repos.MovieGenresRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieGenreService {
    @Autowired
    MovieGenresRepo movieGenresRepo;

    public List<Movie_Genres> findByMovieId(int id){
        return movieGenresRepo.findByMovieId(id);
    }
}
