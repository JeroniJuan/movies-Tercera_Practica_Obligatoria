package com.esliceu.movies.repos;

import com.esliceu.movies.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepo extends JpaRepository<Movie, Integer> {
    List<Movie> findAll();

    List<Movie> findByTitle(String title);
    // Troba totas les peliculas del actor amb la id
    List<Movie> findByMovieCastsPersonId(int person_id);

    List<Movie> findByMovieCastsCharacterName(String character_name);

    // Troba totes les peliculas amb el membre de la crew.
    List<Movie> findByMovieCrewsPersonId(int person_id);

    // Filtra pelicules per genre
    List<Movie> findByMovieGenresGenreGenreName(String genre_name);


}

