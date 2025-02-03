package com.esliceu.movies.services;

import com.esliceu.movies.models.Genre;
import com.esliceu.movies.models.Movie;
import com.esliceu.movies.models.Movie_Genres;
import com.esliceu.movies.models.Movie_GenresKey;
import com.esliceu.movies.repos.GenreRepo;
import com.esliceu.movies.repos.MovieGenresRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieGenreService {
    @Autowired
    MovieGenresRepo movieGenresRepo;

    @Autowired
    GenreRepo genreRepo;

    public List<Movie_Genres> findByMovieId(int movieId) {
        return movieGenresRepo.findByMovieId(movieId);
    }

    public void updateMovieGenres(int movieId, List<Integer> genreIds) {


        movieGenresRepo.deleteByMovieId(movieId);
        for (Integer genreId : genreIds) {
            Genre genre = genreRepo.findById((int) genreId);

            Movie_Genres mg = new Movie_Genres();
            mg.setMovie(new Movie(movieId));
            mg.setGenre(genre);

            movieGenresRepo.save(mg);
        }
    }
}
