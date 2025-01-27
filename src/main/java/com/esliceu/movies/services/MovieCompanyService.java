package com.esliceu.movies.services;

import com.esliceu.movies.models.Movie_Company;
import com.esliceu.movies.models.Movie_CompanyKey;
import com.esliceu.movies.repos.MovieCompanyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieCompanyService {
    @Autowired
    private MovieCompanyRepo movieCompanyRepo;

    public List<Movie_Company> findAll() {
        return movieCompanyRepo.findAll();
    }

    public Movie_Company findById(Movie_CompanyKey id) {
        return movieCompanyRepo.findById(id).orElse(null);
    }

    public Movie_Company save(Movie_Company movieCompany) {
        return movieCompanyRepo.save(movieCompany);
    }

    public void deleteById(Movie_CompanyKey id) {
        movieCompanyRepo.deleteById(id);
    }
}
