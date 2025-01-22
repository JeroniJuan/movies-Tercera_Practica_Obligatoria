package com.esliceu.movies.repos;

import com.esliceu.movies.models.Movie;
import com.esliceu.movies.models.Movie_Cast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieCastRepo extends JpaRepository<Movie_Cast, Long> {
    List<Movie_Cast> findByPersonId(int person_id);

    List<Movie_Cast> findByMovieId(int movie_id);

    List<Movie_Cast> findByPersonPersonName(String name);

}
