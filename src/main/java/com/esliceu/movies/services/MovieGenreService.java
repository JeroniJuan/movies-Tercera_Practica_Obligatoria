package com.esliceu.movies.services;

import com.esliceu.movies.models.Movie_Genres;
import com.esliceu.movies.models.Movie_GenresKey;
import com.esliceu.movies.repos.MovieGenresRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieGenreService {
    @Autowired
    MovieGenresRepo movieGenresRepo;

    public List<Movie_Genres> findByMovieId(int movieId) {
        return movieGenresRepo.findByMovieId(movieId);
    }

    public void updateMovieGenres(int movieId, List<Integer> genreIds) {
        movieGenresRepo.deleteByMovieId(movieId);
        for (Integer genreId : genreIds) {
            Movie_Genres mg = new Movie_Genres(new Movie_GenresKey(movieId, genreId));
            movieGenresRepo.save(mg);
        }
    }

}
