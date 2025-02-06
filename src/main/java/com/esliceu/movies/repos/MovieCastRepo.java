package com.esliceu.movies.repos;

import com.esliceu.movies.models.Movie_Cast;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieCastRepo extends JpaRepository<Movie_Cast, Long> {
    List<Movie_Cast> findByMovieId(int movie_id);

    List<Movie_Cast> findByPersonPersonName(String name);

    void deleteByMovie_Id(int movieId);

    void deleteByGenderId(int id);

    void deleteByPersonId(int id);
}
