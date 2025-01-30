package com.esliceu.movies.controllers;

import com.esliceu.movies.models.Movie_Company;
import com.esliceu.movies.services.MovieCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MovieCompanyController {

    @Autowired
    private MovieCompanyService movieCompanyService;

    // Mostrar lista de Movie_Company
    @GetMapping("/movieCompany")
    public String listMovieCompanies(Model model) {
        model.addAttribute("movieCompanies", movieCompanyService.findAll());
        return "movieCompany-list";
    }

    // Mostrar detalles de Movie_Company
    @GetMapping("/movieCompany/{movieId}/{companyId}")
    public String showMovieCompanyDetails(@PathVariable int movieId, @PathVariable int companyId, Model model) {
        Movie_Company movieCompany = movieCompanyService.findById(movieId, companyId);
        model.addAttribute("movieCompany", movieCompany);
        return "movieCompany-detail";
    }

    @GetMapping("/createMovieCompany")
    public String showCreateForm() {
        return "movieCompany-create";
    }

    @PostMapping("/createMovieCompany")
    public String createMovieCompany(@ModelAttribute Movie_Company movieCompany) {
        movieCompanyService.save(movieCompany);
        return "redirect:/movieCompany";
    }

    @GetMapping("/deleteMovieCompany/{movieId}/{companyId}")
    public String deleteMovieCompany(@PathVariable int movieId, @PathVariable int companyId) {
        movieCompanyService.deleteById(movieId, companyId);
        return "redirect:/movieCompany";
    }

    @PostMapping("/editMovieCompany")
    public String editMovieCompany(@ModelAttribute Movie_Company movieCompany) {
        movieCompanyService.save(movieCompany);
        return "redirect:/movieCompany";
    }
}
