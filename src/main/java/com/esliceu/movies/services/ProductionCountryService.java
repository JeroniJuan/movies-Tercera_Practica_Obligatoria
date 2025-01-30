package com.esliceu.movies.services;

import com.esliceu.movies.models.Production_Country;
import com.esliceu.movies.repos.ProductionCountryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductionCountryService {
    @Autowired
    ProductionCountryRepo productionCountryRepo;
    public List<Production_Country> findByMovieId(int id){
        return productionCountryRepo.findByMovieId(id);
    }
}
