package com.esliceu.movies.controllers;

import com.esliceu.movies.models.Movie;
import com.esliceu.movies.models.Movie_Crew;
import com.esliceu.movies.services.MovieCastService;
import com.esliceu.movies.services.MovieCrewService;
import com.esliceu.movies.services.MovieService;
import com.esliceu.movies.services.PersonService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class SearchController {
    @Autowired
    MovieService movieService;
    @Autowired
    MovieCastService movieCastService;
    @Autowired
    MovieCrewService movieCrewService;
    @Autowired
    PersonService personService;

    @GetMapping("/MovieSearchTitle")
    public String getMovieSearchTitle(Model model, @RequestParam("movie") String movieTitle){
        List<Movie> movieList = movieService.findMovieByTitle(movieTitle);
        model.addAttribute("movies", movieList);
        return "movie-list";
    }

    @GetMapping("/MovieSearchCast")
    public String getMovieSearchCast(Model model, @RequestParam("actor") String ActorName){
        int actor_id = movieCastService.getActorId(ActorName);
        List<Movie> movieList = movieService.findMoviesByCast(actor_id);
        model.addAttribute("movies", movieList);
        return "movie-list";
    }
    @GetMapping("/MovieSearchCharacter")
    public String getMovieSearchCharacter(Model model, @RequestParam("character") String character_name){
        List<Movie> movieList = movieService.findMoviesByCharacter(character_name);
        model.addAttribute("movies", movieList);
        return "movie-list";
    }

    @GetMapping("/MovieSearchDirector")
    public String getMovieSearchDirector(Model model, @RequestParam("director") String director_name) {
        int director_id = personService.findPersonByName(director_name).getId();

        List<Movie_Crew> movieCrewList = movieCrewService.findMovieCrewByPersonId(director_id);

        List<Movie_Crew> directorCrewList = movieCrewList.stream()
                .filter(crew -> "Director".equalsIgnoreCase(crew.getJob()))
                .collect(Collectors.toList());

        List<Movie> movieListDirector = directorCrewList.stream()
                .map(Movie_Crew::getMovie)
                .distinct()
                .collect(Collectors.toList());

        model.addAttribute("movies", movieListDirector);

        return "movie-list";
    }


    @GetMapping("/")
    public String getIndex(Model model, HttpSession session){
        Object loggedInUser = session.getAttribute("loggedInUser");
        System.out.println("Iniciant index amb sessio " + loggedInUser);
        if (loggedInUser != null) {
            model.addAttribute("loggedInUser", loggedInUser);
        }
        return "index";
    }

    @GetMapping("/crud")
    public String getCrud(Model model){
        return "crud";
    };
}
