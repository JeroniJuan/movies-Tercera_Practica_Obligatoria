package com.esliceu.movies.services;

import com.esliceu.movies.models.Movie;
import com.esliceu.movies.models.Movie_Company;
import com.esliceu.movies.models.Movie_CompanyKey;
import com.esliceu.movies.models.Production_Company;
import com.esliceu.movies.repos.MovieCompanyRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MovieCompanyService {
    @Autowired
    private MovieCompanyRepo movieCompanyRepo;

    @Autowired
    private MovieService movieService;

    @Autowired
    private ProductionCompanyService productionCompanyService;

    public List<Movie_Company> findAll() {
        return movieCompanyRepo.findAll();
    }

    public Page<Movie_Company> findAll(Pageable pageable) {
        return movieCompanyRepo.findAll(pageable);
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

    public void deleteById(int movieId, int companyId) {
        movieCompanyRepo.deleteByMovieIdAndCompanyId(movieId, companyId);
    }

    public Movie_Company findById(int movieId, int companyId) {
        Movie_CompanyKey key = new Movie_CompanyKey(movieId, companyId);
        return movieCompanyRepo.findById(key).orElse(null);
    }

    public List<Movie_Company> findByMovieId(int movieId) {
        return movieCompanyRepo.findByMovieId(movieId);
    }

    public void updateMovieCompanies(int movieId, List<Integer> companyIds) {
        Movie movie = movieService.findById(movieId);
        List<Movie_Company> currentCompanies = movieCompanyRepo.findByMovieId(movieId);

        Set<Integer> newCompanyIds = new HashSet<>(companyIds);

        for (Movie_Company mc : currentCompanies) {
            if (!newCompanyIds.contains(mc.getCompany().getId())) {
                movieCompanyRepo.delete(mc);
            }
        }

        for (int companyId : companyIds) {
            Production_Company company = productionCompanyService.findById(companyId);

            boolean exists = currentCompanies.stream()
                    .anyMatch(mc -> mc.getCompany().getId() == companyId);
            if (!exists) {
                Movie_CompanyKey key = new Movie_CompanyKey(movie.getId(), company.getId());
                Movie_Company movieCompany = new Movie_Company();
                movieCompany.setId(key);
                movieCompany.setMovie(movie);
                movieCompany.setCompany(company);
                movieCompanyRepo.save(movieCompany);
            }
        }
    }
    @Transactional
    public void deleteByMovieId(int id) {
        movieCompanyRepo.deleteByMovieId(id);
    }

    public void deleteByCompanyId(int id) {
        movieCompanyRepo.deleteByCompanyId(id);
    }
}