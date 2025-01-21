package com.esliceu.movies.repos;

import com.esliceu.movies.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepo extends JpaRepository<Movie, Long> {
    List<Movie> findAll();

    List<Movie> findByTitle(String title);

}

