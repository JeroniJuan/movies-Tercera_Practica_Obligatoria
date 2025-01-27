package com.esliceu.movies.controllers;

import com.esliceu.movies.models.Country;
import com.esliceu.movies.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CountryController {
    @Autowired
    private CountryService countryService;

    @GetMapping("/countryCRUD")
    public String countryCrud(Model model){
        return "countryCRUD";
    }
}
