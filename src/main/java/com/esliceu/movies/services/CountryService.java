package com.esliceu.movies.services;

import com.esliceu.movies.models.Country;
import com.esliceu.movies.repos.CountryRepo;
import com.esliceu.movies.repos.ProductionCountryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {
    @Autowired
    CountryRepo countryRepo;

    public CountryService(CountryRepo CountryRepo) {
        this.countryRepo = CountryRepo;
    }

    public List<Country> findAll() {
        return countryRepo.findAll();
    }

    public Country findById(int id) {
        return countryRepo.findById(id);
    }

    public Country save(Country country) {
        return countryRepo.save(country);
    }

    public void deleteById(int id) {
        countryRepo.deleteById(id);
    }

    public Country findByName(String countryName) {
        return countryRepo.findByCountryName(countryName);
    }
}
