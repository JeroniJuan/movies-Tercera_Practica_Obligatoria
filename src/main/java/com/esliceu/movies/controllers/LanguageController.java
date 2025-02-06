package com.esliceu.movies.controllers;

import com.esliceu.movies.models.Language;
import com.esliceu.movies.models.Permission;
import com.esliceu.movies.services.LanguageService;
import com.esliceu.movies.services.PermissionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LanguageController {

    @Autowired
    private LanguageService languageService;

    @Autowired
    private PermissionService permissionService;

    @GetMapping("/language")
    public String listLanguages(Model model) {
        model.addAttribute("languages", languageService.findAll());
        return "language-list";
    }

    @GetMapping("/editLanguage")
    public String searchLanguage(@RequestParam String languageName, Model model) {
        Language language = languageService.findByName(languageName);

        model.addAttribute("language", language);
        return "language-detail";
    }

    @GetMapping("/createLanguage")
    public String showCreateForm() {
        return "language-create";
    }

    @PostMapping("/createLanguage")
    public String createLanguage(@ModelAttribute Language language, HttpSession session) {
        int userId = (int) session.getAttribute("loggedInUserId");
        boolean canCreate = permissionService.isUserAuthorized(userId, Permission.permission_name.upload_movie);
        if (canCreate) languageService.save(language);
        return "redirect:/language";
    }

    @PostMapping("/editLanguage")
    public String editLanguage(@ModelAttribute Language language, HttpSession session) {
        int userId = (int) session.getAttribute("loggedInUserId");
        boolean canUpdate = permissionService.isUserAuthorized(userId, Permission.permission_name.modify_movie);
        if (canUpdate) languageService.save(language);
        return "redirect:/language";
    }

    @GetMapping("/deleteLanguage/{id}")
    public String deleteLanguage(@PathVariable int id, HttpSession session) {
        int userId = (int) session.getAttribute("loggedInUserId");
        boolean canDelete = permissionService.isUserAuthorized(userId, Permission.permission_name.remove_movie);
        if (canDelete) languageService.deleteById(id);
        return "redirect:/language";
    }
}
