package com.esliceu.movies.repos;

import com.esliceu.movies.models.Movie_Keywords;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieKeywordRepo extends JpaRepository<Movie_Keywords, Integer> {
    List<Movie_Keywords> findByMovieId(int id);

    void deleteByMovieId(int movieId);
}
