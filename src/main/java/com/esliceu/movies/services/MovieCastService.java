package com.esliceu.movies.services;

import com.esliceu.movies.models.Movie;
import com.esliceu.movies.models.Movie_Cast;
import com.esliceu.movies.repos.MovieCastRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieCastService {
    @Autowired
    MovieCastRepo movieCastRepo;

    public List<Movie_Cast> findMovieCastByPersonId(int person_id){
      return movieCastRepo.findByPersonId(person_id);
    }

    public List<Movie_Cast> findMovieCastByMovieId(String movie_id){
        return movieCastRepo.findByMovieId(movie_id);
    }
}
