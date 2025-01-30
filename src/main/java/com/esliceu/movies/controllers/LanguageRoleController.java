package com.esliceu.movies.controllers;

import com.esliceu.movies.models.Language_Role;
import com.esliceu.movies.models.Permission;
import com.esliceu.movies.services.LanguageRoleService;
import com.esliceu.movies.services.PermissionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LanguageRoleController {

    @Autowired
    private LanguageRoleService languageRoleService;

    @Autowired
    private PermissionService permissionService;

    @GetMapping("/languageRole")
    public String listLanguageRoles(Model model) {
        model.addAttribute("languageRoles", languageRoleService.findAll());
        return "languageRole-list";
    }

    @GetMapping("/searchLanguageRole")
    public String searchLanguageRole(@RequestParam String roleName, Model model) {
        Language_Role languageRole = languageRoleService.findByName(roleName);


        model.addAttribute("languageRole", languageRole);
        return "languageRole-detail";
    }

    @GetMapping("/createLanguageRole")
    public String showCreateForm() {
        return "languageRole-create";
    }

    @PostMapping("/createLanguageRole")
    public String createLanguageRole(@ModelAttribute Language_Role languageRole, HttpSession session) {
        int userId = (int) session.getAttribute("loggedInUserId");
        boolean canCreate = permissionService.isUserAuthorized(userId, Permission.permission_name.upload_movie);
        if (canCreate) languageRoleService.save(languageRole);
        return "redirect:/languageRole";
    }

    @PostMapping("/editLanguageRole")
    public String editLanguageRole(@ModelAttribute Language_Role languageRole, HttpSession session) {
        int userId = (int) session.getAttribute("loggedInUserId");
        boolean canUpdate = permissionService.isUserAuthorized(userId, Permission.permission_name.modify_movie);
        if (canUpdate) languageRoleService.save(languageRole);
        return "redirect:/languageRole";
    }

    @GetMapping("/deleteLanguageRole/{id}")
    public String deleteLanguageRole(@PathVariable int id, HttpSession session) {
        int userId = (int) session.getAttribute("loggedInUserId");
        boolean canDelete = permissionService.isUserAuthorized(userId, Permission.permission_name.remove_movie);
        if (canDelete) languageRoleService.deleteById(id);
        return "redirect:/languageRole";
    }
}
