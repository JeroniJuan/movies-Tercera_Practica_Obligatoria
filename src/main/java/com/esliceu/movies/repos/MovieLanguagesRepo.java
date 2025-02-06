package com.esliceu.movies.repos;

import com.esliceu.movies.models.Movie_Languages;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieLanguagesRepo extends JpaRepository<Movie_Languages, Integer> {

    List<Movie_Languages> findByMovieId(int id);

    void deleteByMovieId(int movieId);

    void deleteByLanguageRoleId(int id);

    void deleteByLanguageId(int id);
}
