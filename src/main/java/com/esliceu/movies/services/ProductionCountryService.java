package com.esliceu.movies.services;

import com.esliceu.movies.models.Country;
import com.esliceu.movies.models.Movie;
import com.esliceu.movies.models.Production_Country;
import com.esliceu.movies.models.Production_CountryKey;
import com.esliceu.movies.repos.ProductionCountryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductionCountryService {
    @Autowired
    ProductionCountryRepo productionCountryRepo;
    public List<Production_Country> findByMovieId(int movieId) {
        return productionCountryRepo.findByMovieId(movieId);
    }

    public void updateProductionCountries(int movieId, List<Integer> countryIds) {
        productionCountryRepo.deleteByMovieId(movieId);
        for (Integer countryId : countryIds) {
            Production_Country pc = new Production_Country(new Production_CountryKey(movieId, countryId));
            productionCountryRepo.save(pc);
        }
    }

    public void updateMovieProductionCountries(int movieId, List<Integer> countryIds) {
        productionCountryRepo.deleteByMovieId(movieId);

        for (Integer countryId : countryIds) {
            Production_Country productionCountry = new Production_Country();
            productionCountry.setMovie(new Movie(movieId));
            productionCountry.setCountry(new Country(countryId));
            productionCountryRepo.save(productionCountry);
        }
    }

}
