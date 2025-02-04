package com.esliceu.movies.controllers;

import com.esliceu.movies.models.*;
import com.esliceu.movies.services.*;
import com.esliceu.movies.utils.Utils;
import jakarta.transaction.Transactional;
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
    PersonService personService;

    @Autowired
    MovieKeywordService movieKeywordService;

    @Autowired
    MovieCompanyService movieCompanyService;

    @Autowired
    MovieCastService movieCastService;

    @Autowired
    MovieCrewService movieCrewService;

    @Autowired
    GenreService genreService;

    @Autowired
    CountryService countryService;

    @Autowired
    LanguageService languageService;

    @Autowired
    KeywordService keywordService;

    @Autowired
    ProductionCompanyService productionCompanyService;

    @GetMapping("/movie")
    public String getMovieDetails(@RequestParam int id, Model model, HttpSession session) {
        Movie movie = movieService.findById(id);

        List<Movie_Genres> movieGenres = movieGenreService.findByMovieId(id);
        List<Production_Country> productionCountries = productionCountryService.findByMovieId(id);
        List<Movie_Languages> movieLanguages = movieLanguagesService.findByMovieId(id);
        List<Movie_Keywords> movieKeywords = movieKeywordService.findByMovieId(id);
        List<Movie_Company> movieCompanies = movieCompanyService.findByMovieId(id);
        List<Movie_Cast> movieCasts = movieCastService.findByMovieId(id);
        List<Movie_Crew> movieCrews = movieCrewService.findByMovieId(id);

        List<Genre> allGenres = genreService.findAll();
        List<Genre> remainingGenres = allGenres.stream()
                .filter(g -> movieGenres.stream().noneMatch(mg -> mg.getGenre().getId() == g.getId()))
                .toList();

        List<Country> allCountries = countryService.findAll();
        List<Country> remainingCountries = allCountries.stream()
                .filter(c -> productionCountries.stream().noneMatch(pc -> pc.getCountry().getId() == c.getId()))
                .toList();

        List<Language> allLanguages = languageService.findAll();
        List<Language> remainingLanguages = allLanguages.stream()
                .filter(l -> movieLanguages.stream().noneMatch(ml -> ml.getLanguage().getId() == l.getId()))
                .toList();

        List<Keyword> allKeywords = keywordService.findAll();
        List<Keyword> remainingKeywords = allKeywords.stream()
                .filter(k -> movieKeywords.stream().noneMatch(mk -> mk.getKeyword().getId() == k.getId()))
                .toList();

        List<Production_Company> allCompanies = productionCompanyService.findAll();
        List<Production_Company> remainingCompanies = allCompanies.stream()
                .filter(c -> movieCompanies.stream().noneMatch(mc -> mc.getCompany().getId() == c.getId()))
                .toList();

        model.addAttribute("movie", movie);
        model.addAttribute("movieGenres", movieGenres);
        model.addAttribute("productionCountries", productionCountries);
        model.addAttribute("movieLanguages", movieLanguages);
        model.addAttribute("movieKeywords", movieKeywords);
        model.addAttribute("movieCompanies", movieCompanies);
        model.addAttribute("movieCasts", movieCasts);
        model.addAttribute("movieCrews", movieCrews);

        model.addAttribute("remainingGenres", remainingGenres);
        model.addAttribute("remainingCountries", remainingCountries);
        model.addAttribute("remainingLanguages", remainingLanguages);
        model.addAttribute("remainingKeywords", remainingKeywords);
        model.addAttribute("remainingCompanies", remainingCompanies);

        return "movie-details";
    }

    @PostMapping("/movie")
    @Transactional
    public String updateMovie(@RequestParam int id,
                              @RequestParam(required = false) String title,
                              @RequestParam(required = false) String description,
                              @RequestParam(required = false) Integer budget,
                              @RequestParam(required = false) String homepage,
                              @RequestParam(required = false) String release_date,
                              @RequestParam(required = false) List<Integer> genreIds,
                              @RequestParam(required = false) List<Integer> countryIds,
                              @RequestParam(required = false) List<Integer> languageIds,
                              @RequestParam(required = false) List<Integer> languageRoleIds,
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
            return "redirect:/login";
        }

        boolean canModify = permissionService.isUserAuthorized(userId, Permission.permission_name.modify_movie);
        if (!canModify) {
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
        if (languageIds != null && languageRoleIds != null && languageIds.size() == languageRoleIds.size()) {
            movieLanguagesService.updateMovieLanguages(id, languageIds, languageRoleIds);
        }
        if (keywordIds != null) {
            movieKeywordService.updateMovieKeywords(id, keywordIds);
        }
        if (companyIds != null) {
            movieCompanyService.updateMovieCompanies(id, companyIds);
        }
        if (castIds != null && characterNames != null && castIds.size() == characterNames.size()) {
            movieCastService.updateMovieCast(id, castIds, characterNames);
        }
        if (crewIds != null && jobTitles != null && crewIds.size() == jobTitles.size()) {
            movieCrewService.updateMovieCrew(id, crewIds, jobTitles);
        }
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
