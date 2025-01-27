package com.esliceu.movies.services;

import com.esliceu.movies.models.Country;
import com.esliceu.movies.repos.CountryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {
    @Autowired
    CountryRepo CountryRepo;

    public CountryService(CountryRepo CountryRepo) {
        this.CountryRepo = CountryRepo;
    }

    public List<Country> findAll() {
        return CountryRepo.findAll();
    }

    public Country findById(int id) {
        return CountryRepo.findByCountryId(id);
    }

    public Country save(Country country) {
        return CountryRepo.save(country);
    }

    public void deleteById(int id) {
        CountryRepo.deleteById(id);
    }
}
