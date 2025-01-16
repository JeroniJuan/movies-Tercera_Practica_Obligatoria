package com.esliceu.movies.controllers;

import com.esliceu.movies.models.Movie;
import com.esliceu.movies.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class SearchController {
    @Autowired
    MovieService movieService;

    @GetMapping("/movieSearch")
    public String getIndex(Model model){
        List<Movie> movieList = movieService.getAllMovies();
        model.addAttribute("movies", movieList);
        return "MovieSearch";
    }
}
