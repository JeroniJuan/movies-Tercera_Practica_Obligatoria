package com.esliceu.movies.controllers;

import com.esliceu.movies.models.*;
import com.esliceu.movies.services.*;
import com.esliceu.movies.utils.Utils;
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
        model.addAttribute("productionCountries", productionCountries);
        model.addAttribute("movieLanguages", movieLanguages);
        model.addAttribute("movieGenres", movieGenres);
        model.addAttribute("movieKeywords", movieKeywords);
        model.addAttribute("movieCompanies", movieCompanies);
        model.addAttribute("movieCasts", movieCasts);
        model.addAttribute("movieCrews", movieCrews);

        return "movie-details";
    }


    @PostMapping("/movie")
    public String updateMovie(@RequestParam int id,
                              @RequestParam(required = false) String title,
                              @RequestParam(required = false) String description,
                              @RequestParam(required = false) Integer budget,
                              @RequestParam(required = false) String homepage,
                              @RequestParam(required = false) String release_date,
                              @RequestParam(required = false) List<Integer> genreIds,
                              @RequestParam(required = false) List<Integer> countryIds,
                              @RequestParam(required = false) List<Integer> languageIds,
                              @RequestParam(required = false) List<Integer> keywordIds,
                              @RequestParam(required = false) List<Integer> companyIds,
                              @RequestParam(required = false) List<Integer> castIds,
                              @RequestParam(required = false) List<String> characterNames,
                              @RequestParam(required = false) List<Integer> crewIds,
                              @RequestParam(required = false) List<String> jobTitles,
                              HttpSession session) {

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

        if (title != null) movie.setTitle(title);
        if (description != null) movie.setOverview(description);
        if (budget != null) movie.setBudget(budget);
        if (homepage != null) movie.setHomepage(homepage);
        if (release_date != null) {
            movie.setRelease_date(Utils.stringToLocalDate(release_date));
        }

        movieService.save(movie);

        if (genreIds != null) {
            movieGenreService.updateMovieGenres(id, genreIds);
        }
        if (countryIds != null) {
            productionCountryService.updateMovieProductionCountries(id, countryIds);
        }
        if (languageIds != null) {
            movieLanguagesService.updateMovieLanguages(id, languageIds);
        }
        if (keywordIds != null) {
            movieKeywordService.updateMovieKeywords(id, keywordIds);
        }
        if (companyIds != null) {
            movieCompanyService.updateMovieCompanies(id, companyIds);
        }
        if (castIds != null && characterNames != null) {
            movieCastService.updateMovieCast(id, castIds, characterNames);
        }
        if (crewIds != null && jobTitles != null) {
            movieCrewService.updateMovieCrew(id, crewIds, jobTitles);
        }

        System.out.println("Actualitzant pelicula i totes les seves relacions");

        return "redirect:/movie?id=" + id;
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
