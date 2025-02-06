package com.esliceu.movies.controllers;

import com.esliceu.movies.models.Movie_Company;
import com.esliceu.movies.models.Permission;
import com.esliceu.movies.services.MovieCompanyService;
import com.esliceu.movies.services.PermissionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MovieCompanyController {

    @Autowired
    private MovieCompanyService movieCompanyService;
    @Autowired
    private PermissionService permissionService;

    @GetMapping("/movieCompany")
    public String listMovieCompanies(Model model, Pageable pageable) {
        Page<Movie_Company> movieCompaniesPage = movieCompanyService.findAll(pageable);
        model.addAttribute("movieCompanies", movieCompaniesPage.getContent());
        model.addAttribute("currentPage", movieCompaniesPage.getNumber());
        model.addAttribute("totalPages", movieCompaniesPage.getTotalPages());
        return "movieCompany-list";
    }

    @GetMapping("/editMovieCompany/{movieId}/{companyId}")
    public String showDetail(@PathVariable int movieId, @PathVariable int companyId, Model model) {
        System.out.println("Movie id = " + movieId);
        System.out.println("CompanyId = " + companyId);
        Movie_Company movieCompany = movieCompanyService.findById(movieId, companyId);
        model.addAttribute("movieCompany", movieCompany);
        return "movieCompany-detail";
    }

    @GetMapping("/createMovieCompany")
    public String showCreateForm() {
        return "movieCompany-create";
    }

    @PostMapping("/createMovieCompany")
    public String createMovieCompany(@ModelAttribute Movie_Company movieCompany, HttpSession session) {
        int userId = (int) session.getAttribute("loggedInUserId");
        boolean canCreate = permissionService.isUserAuthorized(userId, Permission.permission_name.upload_movie);
        if (canCreate) movieCompanyService.save(movieCompany);
        return "redirect:/movieCompany";
    }

    @GetMapping("/deleteMovieCompany/{movieId}/{companyId}")
    public String deleteMovieCompany(@PathVariable int movieId, @PathVariable int companyId, HttpSession session) {
        int userId = (int) session.getAttribute("loggedInUserId");
        boolean canDelete = permissionService.isUserAuthorized(userId, Permission.permission_name.remove_movie);
        if (canDelete) {
            movieCompanyService.deleteById(movieId, companyId);
        }
        return "redirect:/movieCompany";
    }

    @PostMapping("/editMovieCompany")
    public String editMovieCompany(@ModelAttribute Movie_Company movieCompany) {
        movieCompanyService.save(movieCompany);
        return "redirect:/movieCompany";
    }
}
