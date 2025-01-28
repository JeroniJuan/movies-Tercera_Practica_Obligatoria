package com.esliceu.movies.services;

import com.esliceu.movies.models.Country;
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
        return movieRepo.findByTitleContainingIgnoreCase(title);
    }

    public List<Movie> findMoviesByCast(int person_id){
        return movieRepo.findByMovieCastsPersonId(person_id);
    }

    public List<Movie> findMoviesByCharacter(String character_name){
        return movieRepo.findByMovieCastsCharacterName(character_name);
    }

    public List<Movie> findMoviesByCrew(int person_id){
        return movieRepo.findByMovieCrewsPersonId(person_id);
    }

    public Movie save(Movie movie) {
        return movieRepo.save(movie);
    }

    public void deleteById(int id) {
        movieRepo.deleteById(id);
    }

    public Movie findById(int id) {
        return movieRepo.findById(id).orElse(null);
    }
}
