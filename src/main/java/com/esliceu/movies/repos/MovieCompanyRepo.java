package com.esliceu.movies.repos;

import com.esliceu.movies.models.Movie_Company;
import com.esliceu.movies.models.Movie_CompanyKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieCompanyRepo extends JpaRepository<Movie_Company, Movie_CompanyKey> {

    List<Movie_Company> findByMovieId(int id);
}
