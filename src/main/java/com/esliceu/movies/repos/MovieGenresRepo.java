package com.esliceu.movies.repos;

import com.esliceu.movies.models.Movie_Genres;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieGenresRepo extends JpaRepository<Movie_Genres, Integer> {
    List<Movie_Genres> findByMovieId(int id);

    void deleteByMovieId(int movieId);
}
