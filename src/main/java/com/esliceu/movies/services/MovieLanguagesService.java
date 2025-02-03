package com.esliceu.movies.services;

import com.esliceu.movies.models.Movie_Languages;
import com.esliceu.movies.models.Movie_LanguagesKey;
import com.esliceu.movies.repos.MovieLanguagesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieLanguagesService {
    @Autowired
    private MovieLanguagesRepo movieLanguagesRepo;

    public List<Movie_Languages> findByMovieId(int movieId) {
        return movieLanguagesRepo.findByMovieId(movieId);
    }

    public void updateMovieLanguages(int movieId, List<Integer> languageIds) {
        movieLanguagesRepo.deleteByMovieId(movieId);
        for (Integer languageId : languageIds) {
            Movie_Languages ml = new Movie_Languages(new Movie_LanguagesKey(movieId, languageId));
            movieLanguagesRepo.save(ml);
        }
    }

}
