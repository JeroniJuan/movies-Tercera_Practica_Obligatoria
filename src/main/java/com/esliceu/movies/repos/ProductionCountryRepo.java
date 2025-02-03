package com.esliceu.movies.repos;

import com.esliceu.movies.models.Production_Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductionCountryRepo extends JpaRepository<Production_Country, Integer> {

    List<Production_Country> findByMovieId(int id);

    void deleteByMovieId(int movieId);
}
