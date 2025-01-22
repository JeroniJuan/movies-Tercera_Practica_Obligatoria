package com.esliceu.movies.controllers;

import com.esliceu.movies.models.Movie;
import com.esliceu.movies.services.MovieCastService;
import com.esliceu.movies.services.MovieCrewService;
import com.esliceu.movies.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchController {
    @Autowired
    MovieService movieService;
    @Autowired
    MovieCastService movieCastService;
    @Autowired
    MovieCrewService movieCrewService;

    @GetMapping("/MovieSearchTitle")
    public String getMovieSearchTitle(Model model, @RequestParam("movie") String movieTitle){
        List<Movie> movieList = movieService.findMovieByTitle(movieTitle);
        model.addAttribute("movies", movieList);
        return "MovieSearch";
    }

    @GetMapping("/MovieSearchCast")
    public String getMovieSearchCast(Model model, @RequestParam("actor") String ActorName){
        int actor_id = movieCastService.getActorId(ActorName);
        List<Movie> movieList = movieService.findMoviesByCast(actor_id);
        model.addAttribute("movies", movieList);
        return "MovieSearch";
    }
    @GetMapping("/MovieSearchCharacter")
    public String getMovieSearchCharacter(Model model, @RequestParam("character") String character_name){
        List<Movie> movieList = movieService.findMoviesByCharacter(character_name);
        model.addAttribute("movies", movieList);
        return "MovieSearch";
    }

    @GetMapping("/MovieSearchDirector")
    public String getMovieSearchDirector(Model model, @RequestParam("director") String director_name){
        int director_id = movieCrewService.getDirectorByName(director_name);
        List<Movie> movieList = movieService.findMoviesByCrew(director_id);
        model.addAttribute("movies", movieList);
        return "MovieSearch";
    }

    @GetMapping("/")
    public String getIndex(Model model){
        return "index";
    }
}
