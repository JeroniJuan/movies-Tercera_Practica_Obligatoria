package com.esliceu.movies.controllers;

import com.esliceu.movies.models.Genre;
import com.esliceu.movies.models.Permission;
import com.esliceu.movies.services.GenreService;
import com.esliceu.movies.services.PermissionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class GenreController {

    @Autowired
    private GenreService genreService;

    @Autowired
    private PermissionService permissionService;

    @GetMapping("/genre")
    public String listGenres(Model model) {
        model.addAttribute("genres", genreService.findAll());
        return "genre-list";
    }

    @GetMapping("/editGenre")
    public String searchGenre(@RequestParam String genreName, Model model) {
        Genre genre = genreService.findByName(genreName);

        model.addAttribute("genre", genre);
        return "genre-detail";
    }

    @GetMapping("/createGenre")
    public String showCreateForm() {
        return "genre-create";
    }

    @PostMapping("/createGenre")
    public String createGenre(@ModelAttribute Genre genre, HttpSession session) {
        int userId = (int) session.getAttribute("loggedInUserId");
        boolean canCreate = permissionService.isUserAuthorized(userId, Permission.permission_name.upload_movie);
        if (canCreate) genreService.save(genre);
        return "redirect:/genre";
    }

    @PostMapping("/editGenre")
    public String editGenre(@ModelAttribute Genre genre, HttpSession session) {
        int userId = (int) session.getAttribute("loggedInUserId");
        boolean canUpdate = permissionService.isUserAuthorized(userId, Permission.permission_name.modify_movie);
        if (canUpdate) genreService.save(genre);
        return "redirect:/genre";
    }

    @GetMapping("/deleteGenre/{id}")
    public String deleteGenre(@PathVariable int id, HttpSession session) {
        int userId = (int) session.getAttribute("loggedInUserId");
        boolean canDelete = permissionService.isUserAuthorized(userId, Permission.permission_name.remove_movie);
        if (canDelete) genreService.deleteById(id);
        return "redirect:/genre";
    }
}
