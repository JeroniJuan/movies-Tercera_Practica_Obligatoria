package com.esliceu.movies.repos;

import com.esliceu.movies.models.Movie_Crew;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieCrewRepo extends JpaRepository<Movie_Crew, Long> {

    List<Movie_Crew> findByPersonPersonName(String personName);

    List<Movie_Crew> findByPersonId(int personId);

    List<Movie_Crew> findByMovieId(int id);

    void deleteByMovieId(int movieId);

    void deleteByMovie_Id(int id);

    void deleteByPersonId(int id);
}
