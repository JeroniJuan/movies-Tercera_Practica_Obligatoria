package com.esliceu.movies.services;

import com.esliceu.movies.models.Movie_Languages;
import com.esliceu.movies.repos.MovieLanguagesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieLanguagesService {
    @Autowired
    private MovieLanguagesRepo movieLangagesRepo;

    public List<Movie_Languages> findByMovieId(int id){
        return movieLangagesRepo.findByMovieId(id);
    }
}
