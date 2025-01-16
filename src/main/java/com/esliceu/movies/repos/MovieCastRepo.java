package com.esliceu.movies.repos;

import com.esliceu.movies.models.Movie;
import com.esliceu.movies.models.Movie_Cast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieCastRepo extends JpaRepository<Movie_Cast, Long> {
    @Query("SELECT * FROM Movie_Cast WHERE person_id = :person_id")
    List<Movie> findMovieCastByPersonId(String person_id);

    @Query("SELECT * FROM movie INNER JOIN movie_cast WHERE movie.movie_id = movie_cast.movie_id AND movie_cast.person_id = :person_id")
    List<Movie> findMoviesByCastPersonId(String person_id);
}
