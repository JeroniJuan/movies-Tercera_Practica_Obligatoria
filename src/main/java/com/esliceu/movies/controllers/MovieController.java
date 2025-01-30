package com.esliceu.movies.controllers;

import com.esliceu.movies.models.*;
import com.esliceu.movies.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

import java.util.List;

@Controller
public class MovieController {

    @Autowired
    MovieService movieService;

    @Autowired
    PermissionService permissionService;

    @Autowired
    ProductionCountryService productionCountryService;

    @Autowired
    MovieLanguagesService movieLanguagesService;

    @Autowired
    MovieGenreService movieGenreService;

    @Autowired
    MovieKeywordService movieKeywordService;

    @Autowired
    MovieCompanyService movieCompanyService;

    @Autowired
    MovieCastService movieCastService;

    @Autowired
    MovieCrewService movieCrewService;

    @GetMapping("/movie")
    public String getMovieDetails(@RequestParam int id, Model model, HttpSession session) {
        Movie movie = movieService.findById(id);
        List<Production_Country> productionCountries = productionCountryService.findByMovieId(id);
        List<Movie_Languages> movieLanguages = movieLanguagesService.findByMovieId(id);
        List<Movie_Genres> movieGenres = movieGenreService.findByMovieId(id);
        List<Movie_Keywords> movieKeywords = movieKeywordService.findByMovieId(id);
        List<Movie_Company> movieCompanies = movieCompanyService.findByMovieId(id);
        List<Movie_Cast> movieCasts = movieCastService.findByMovieId(id);
        List<Movie_Crew> movieCrews = movieCrewService.findByMovieId(id);
        model.addAttribute("movie", movie);

        return "movie-details";
    }

    @PostMapping("/movie")
    public String updateMovie(@RequestParam int id, @RequestParam(required = false) String title,
                              @RequestParam(required = false) String description,
                              @RequestParam(required = false) String action, HttpSession session, Model model) {
        Movie movie = movieService.findById(id);

        if (movie == null) {
            return "redirect:/";
        }

        Integer userId = (Integer) session.getAttribute("loggedInUserId");
        if (userId == null) {
            System.out.println("No user");
            return "redirect:/login";
        }

        boolean canModify = permissionService.isUserAuthorized(userId, Permission.permission_name.modify_movie);
        if (!canModify) {
            System.out.println("No pots modificar");
            return "redirect:/";
        }

        if ("delete".equals(action)) {
            movieService.deleteById(id);
            return "redirect:/";
        }

        movie.setTitle(title);
        movie.setOverview(description);
        System.out.println("Actualitzant pelicula");
        movieService.save(movie);

        return "redirect:/";
    }

    @GetMapping("/delMovie")
    public String deleteMovie(@RequestParam int id, HttpSession session){
        int userId = (int) session.getAttribute("loggedInUserId");
        if (permissionService.isUserAuthorized(userId, Permission.permission_name.remove_movie)){
            movieService.deleteById(id);
        }
        return "redirect:/";
    }
}
