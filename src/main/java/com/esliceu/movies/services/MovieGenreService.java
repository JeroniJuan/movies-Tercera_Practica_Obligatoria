package com.esliceu.movies.services;

import com.esliceu.movies.models.Genre;
import com.esliceu.movies.models.Movie;
import com.esliceu.movies.models.Movie_Genres;
import com.esliceu.movies.models.Movie_GenresKey;
import com.esliceu.movies.repos.GenreRepo;
import com.esliceu.movies.repos.MovieGenresRepo;
import com.esliceu.movies.repos.MovieRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MovieGenreService {
    @Autowired
    MovieGenresRepo movieGenresRepo;

    @Autowired
    GenreRepo genreRepo;

    @Autowired
    MovieRepo movieRepo;

    public List<Movie_Genres> findByMovieId(int movieId) {
        return movieGenresRepo.findByMovieId(movieId);
    }

    public void updateMovieGenres(int movieId, List<Integer> genreIds) {
        Movie movie = movieRepo.findById(movieId);

        List<Movie_Genres> currentGenres = movieGenresRepo.findByMovieId(movieId);

        Set<Integer> newGenreIds = new HashSet<>(genreIds);

        for (Movie_Genres mg : currentGenres) {
            if (!newGenreIds.contains(mg.getGenre().getId())) {
                movieGenresRepo.delete(mg);
            }
        }

        for (int genreId : genreIds) {
            Genre genre = genreRepo.findById(genreId);

            boolean exists = currentGenres.stream()
                    .anyMatch(mg -> mg.getGenre().getId() == genreId);
            if (!exists) {
                Movie_GenresKey key = new Movie_GenresKey(movie.getId(), genre.getId());
                Movie_Genres mg = new Movie_Genres();
                mg.setId(key);
                mg.setMovie(movie);
                mg.setGenre(genre);

                movieGenresRepo.save(mg);
            }
        }
    }

    @Transactional
    public void deleteByMovieId(int id) {
        movieGenresRepo.deleteByMovieId(id);
    }
}
