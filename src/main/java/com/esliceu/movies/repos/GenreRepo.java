package com.esliceu.movies.repos;

import com.esliceu.movies.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenreRepo extends JpaRepository<Genre, Integer> {
    List<Genre> findAll();

    Genre findById(int id);

    Genre findByGenreName(String genreName);
}
