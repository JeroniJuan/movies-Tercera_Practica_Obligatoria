package com.esliceu.movies.controllers;

import com.esliceu.movies.models.Movie;
import com.esliceu.movies.services.MovieService;
import com.esliceu.movies.services.PermissionService;
import com.esliceu.movies.models.User;
import com.esliceu.movies.models.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

@Controller
public class MovieController {

    @Autowired
    MovieService movieService;

    @Autowired
    PermissionService permissionService;

    @GetMapping("/movie")
    public String getMovieDetails(@RequestParam int id, Model model, HttpSession session) {
        Movie movie = movieService.findById(id);
        model.addAttribute("movie", movie);

        String loggedInUser = (String) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            boolean canModify = permissionService.isUserAuthorized(loggedInUser, Permission.permission_name.modify_movie);
            model.addAttribute("canModify", canModify);
            return "movie-details";
        }

        return "redirect:/";
    }

    @PostMapping("/movie")
    public String updateMovie(@RequestParam int id, @RequestParam(required = false) String title,
                              @RequestParam(required = false) String description,
                              @RequestParam(required = false) String action, HttpSession session, Model model) {
        Movie movie = movieService.findById(id);

        if (movie == null) {
            return "redirect:/";
        }

        String loggedInUser = (String) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            System.out.println("No user");
            return "redirect:/login";
        }

        boolean canModify = permissionService.isUserAuthorized(loggedInUser, Permission.permission_name.modify_movie);
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
    public String deleteMovie(@RequestParam int id){
        movieService.deleteById(id);
        return "redirect:/";
    }
}
