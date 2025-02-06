package com.esliceu.movies.services;

import com.esliceu.movies.models.Country;
import com.esliceu.movies.models.Movie;
import com.esliceu.movies.models.Production_Country;
import com.esliceu.movies.models.Production_CountryKey;
import com.esliceu.movies.repos.ProductionCountryRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProductionCountryService {
    @Autowired
    ProductionCountryRepo productionCountryRepo;
    
    @Autowired
    MovieService movieService;

    @Autowired
    CountryService countryService;
    
    public List<Production_Country> findByMovieId(int movieId) {
        return productionCountryRepo.findByMovieId(movieId);
    }

    public void updateMovieProductionCountries(int movieId, List<Integer> countryIds) {
        Movie movie = movieService.findById(movieId);
        List<Production_Country> currentCountries = productionCountryRepo.findByMovieId(movieId);

        Set<Integer> newCountryIds = new HashSet<>(countryIds);

        for (Production_Country pc : currentCountries) {
            if (!newCountryIds.contains(pc.getCountry().getId())) {
                productionCountryRepo.delete(pc);
            }
        }

        for (int countryId : countryIds) {
            Country country = countryService.findById(countryId);

            boolean exists = currentCountries.stream()
                    .anyMatch(pc -> pc.getCountry().getId() == countryId);
            if (!exists) {
                Production_CountryKey key = new Production_CountryKey(movie.getId(), country.getId());
                Production_Country productionCountry = new Production_Country();
                productionCountry.setId(key);
                productionCountry.setMovie(movie);
                productionCountry.setCountry(country);

                productionCountryRepo.save(productionCountry);
            }
        }
    }

    @Transactional
    public void deleteByMovieId(int id) {
        productionCountryRepo.deleteByMovieId(id);
    }
    @Transactional
    public void deleteByCountryId(int id) {
        productionCountryRepo.deleteByCountryId(id);
    }
}
