package com.esliceu.movies.services;

import com.esliceu.movies.models.Production_Company;
import com.esliceu.movies.repos.ProductionCompanyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductionCompanyService {
    @Autowired
    private ProductionCompanyRepo productionCompanyRepo;

    public List<Production_Company> findAll() {
        return productionCompanyRepo.findAll();
    }

    public Production_Company findById(int id) {
        return productionCompanyRepo.findById(id).orElse(null);
    }

    public Production_Company save(Production_Company productionCompany) {
        return productionCompanyRepo.save(productionCompany);
    }

    public void deleteById(int id) {
        productionCompanyRepo.deleteById(id);
    }
}
