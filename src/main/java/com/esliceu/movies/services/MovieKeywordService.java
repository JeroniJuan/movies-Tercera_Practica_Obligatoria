package com.esliceu.movies.services;

import com.esliceu.movies.models.Movie_Keywords;
import com.esliceu.movies.repos.MovieKeywordRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieKeywordService {
    @Autowired
    MovieKeywordRepo movieKeywordRepo;

    public List<Movie_Keywords> findByMovieId(int id){
        return movieKeywordRepo.findByMovieId(id);
    }
}
