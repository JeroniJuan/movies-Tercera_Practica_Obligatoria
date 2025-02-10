package com.esliceu.movies.controllers;

import com.esliceu.movies.models.*;
import com.esliceu.movies.services.*;
import com.esliceu.movies.utils.Utils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

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
    GenderService genderService;

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
        model.addAttribute("allLanguages", allLanguages);
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
                              @RequestParam(required = false) List<String> languageRoleIds,
                              @RequestParam(required = false) List<Integer> keywordIds,
                              @RequestParam(required = false) List<Integer> companyIds,
                              @RequestParam(required = false) List<String> actorNames,
                              @RequestParam(required = false) List<Integer> actorGenderIds,
                              @RequestParam(required = false) List<String> actorCharacterNames,
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
            movieGenreService.updateMovieGenres(movie.getId(), genreIds);
        }

        if (countryIds != null) {
            productionCountryService.updateMovieProductionCountries(movie.getId(), countryIds);
        }

        if (languageRoleIds != null) {
            List<Integer> languageIds = new ArrayList<>();
            List<Integer> roleIds = new ArrayList<>();

            for (String languageRoleId : languageRoleIds) {
                String[] parts = languageRoleId.split(":");
                if (parts.length == 2) {
                    languageIds.add(Integer.parseInt(parts[0]));
                    roleIds.add(Integer.parseInt(parts[1]));
                }
            }

            if (!languageIds.isEmpty() && !roleIds.isEmpty()) {
                movieLanguagesService.updateMovieLanguages(movie.getId(), languageIds, roleIds);
            }
        }

        if (keywordIds != null) {
            movieKeywordService.updateMovieKeywords(movie.getId(), keywordIds);
        }

        if (companyIds != null) {
            movieCompanyService.updateMovieCompanies(movie.getId(), companyIds);
        }

        if (actorNames != null && actorGenderIds != null && actorCharacterNames != null) {
            if (actorNames.size() == actorGenderIds.size() && actorNames.size() == actorCharacterNames.size()) {
                movieCastService.deleteByMovieId(movie.getId());

                for (int i = 0; i < actorNames.size(); i++) {
                    String actorNameWithCharacter = actorNames.get(i);

                    String actorName = actorNameWithCharacter.split(" - ")[0];

                    Integer genderId = actorGenderIds.get(i);
                    String characterName = actorCharacterNames.get(i);

                    System.out.println("Actor name = " + actorName);

                    Person actor = personService.findPersonByName(actorName);
                    if (actor == null) {
                        continue;
                    }

                    Gender gender = genderService.findById(genderId);

                    if (actor != null && gender != null) {
                        Movie_Cast movieCast = new Movie_Cast();
                        Movie_CastKey movieCastKey = new Movie_CastKey(movie.getId(), actor.getId());
                        movieCast.setId(movieCastKey);
                        movieCast.setMovie(movie);
                        movieCast.setPerson(actor);
                        movieCast.setGender(gender);
                        movieCast.setCharacterName(characterName);
                        movieCast.setCast_order(i);

                        movieCastService.save(movieCast);
                    }
                }
            } else {
                return "redirect:/";
            }
        }

        return "redirect:/movie?id=" + movie.getId();
    }



    @GetMapping("/createMovie")
    public String createMovieForm(Model model, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("loggedInUserId");
        if (userId == null) {
            return "redirect:/login";
        }

        boolean canModify = permissionService.isUserAuthorized(userId, Permission.permission_name.modify_movie);
        if (!canModify) {
            return "redirect:/";
        }

        List<Genre> allGenres = genreService.findAll();
        List<Country> allCountries = countryService.findAll();
        List<Language> allLanguages = languageService.findAll();
        List<Keyword> allKeywords = keywordService.findAll();
        List<Production_Company> allCompanies = productionCompanyService.findAll();

        model.addAttribute("allGenres", allGenres);
        model.addAttribute("allCountries", allCountries);
        model.addAttribute("allLanguages", allLanguages);
        model.addAttribute("allKeywords", allKeywords);
        model.addAttribute("allCompanies", allCompanies);

        model.addAttribute("movie", new Movie());

        return "movie-create";
    }

    @PostMapping("/createMovie")
    @Transactional
    public String createMovie(@RequestParam(required = false) String title,
                              @RequestParam(required = false) String description,
                              @RequestParam(required = false) Integer budget,
                              @RequestParam(required = false) String homepage,
                              @RequestParam(required = false) String release_date,
                              @RequestParam(required = false) List<Integer> genreIds,
                              @RequestParam(required = false) List<Integer> countryIds,
                              @RequestParam(required = false) List<String> languageRoleIds,
                              @RequestParam(required = false) List<Integer> keywordIds,
                              @RequestParam(required = false) List<Integer> companyIds,
                              @RequestParam(required = false) List<String> actorNames,
                              @RequestParam(required = false) List<Integer> actorGenderIds,
                              @RequestParam(required = false) List<String> actorCharacterNames,
                              HttpSession session) {

        Integer userId = (Integer) session.getAttribute("loggedInUserId");
        if (userId == null) {
            return "redirect:/login";
        }

        boolean canModify = permissionService.isUserAuthorized(userId, Permission.permission_name.modify_movie);
        if (!canModify) {
            return "redirect:/";
        }

        Movie movie = new Movie();
        if (title != null) movie.setTitle(title);
        if (description != null) movie.setOverview(description);
        if (budget != null) movie.setBudget(budget);
        if (homepage != null) movie.setHomepage(homepage);
        if (release_date != null) {
            movie.setRelease_date(Utils.stringToLocalDate(release_date));
        }

        movieService.save(movie);

        if (genreIds != null) {
            movieGenreService.updateMovieGenres(movie.getId(), genreIds);
        }

        if (countryIds != null) {
            productionCountryService.updateMovieProductionCountries(movie.getId(), countryIds);
        }

        if (languageRoleIds != null) {
            List<Integer> languageIds = new ArrayList<>();
            List<Integer> roleIds = new ArrayList<>();

            for (String languageRoleId : languageRoleIds) {
                String[] parts = languageRoleId.split(":");
                if (parts.length == 2) {
                    languageIds.add(Integer.parseInt(parts[0]));
                    roleIds.add(Integer.parseInt(parts[1]));
                }
            }

            if (!languageIds.isEmpty() && !roleIds.isEmpty()) {
                movieLanguagesService.updateMovieLanguages(movie.getId(), languageIds, roleIds);
            }
        }

        if (keywordIds != null) {
            movieKeywordService.updateMovieKeywords(movie.getId(), keywordIds);
        }

        if (companyIds != null) {
            movieCompanyService.updateMovieCompanies(movie.getId(), companyIds);
        }

        if (actorNames != null && actorGenderIds != null && actorCharacterNames != null) {
            for (int i = 0; i < actorNames.size(); i++) {
                String actorName = actorNames.get(i);
                Integer genderId = actorGenderIds.get(i);
                String characterName = actorCharacterNames.get(i);

                Person actor = personService.findPersonByName(actorName);
                if (actor == null) {
                    continue;
                }

                Gender gender = genderService.findById(genderId);

                if (actor != null && gender != null) {
                    Movie_Cast movieCast = new Movie_Cast();
                    Movie_CastKey movieCastKey = new Movie_CastKey(movie.getId(), actor.getId());
                    movieCast.setId(movieCastKey);
                    movieCast.setMovie(movie);
                    movieCast.setPerson(actor);
                    movieCast.setGender(gender);
                    movieCast.setCharacterName(characterName);
                    movieCast.setCast_order(i);

                    movieCastService.save(movieCast);
                }
            }
        }

        return "redirect:/movie?id=" + movie.getId();
    }





    @GetMapping("/delMovie")
    public String deleteMovie(@RequestParam int id, HttpSession session){
        int userId = (int) session.getAttribute("loggedInUserId");

        if (permissionService.isUserAuthorized(userId, Permission.permission_name.remove_movie)){

            movieGenreService.deleteByMovieId(id);
            productionCountryService.deleteByMovieId(id);
            movieLanguagesService.deleteByMovieId(id);
            movieKeywordService.deleteByMovieId(id);
            movieCompanyService.deleteByMovieId(id);
            movieCastService.deleteByMovieId(id);
            movieCrewService.deleteByMovieId(id);

            movieService.deleteById(id);
        }

        return "redirect:/";
    }

}
