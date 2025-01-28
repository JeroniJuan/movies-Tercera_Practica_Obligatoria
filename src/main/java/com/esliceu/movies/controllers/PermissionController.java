package com.esliceu.movies.controllers;

import com.esliceu.movies.models.Autoritzation;
import com.esliceu.movies.models.Permission;
import com.esliceu.movies.models.User;
import com.esliceu.movies.services.AutoritzationService;
import com.esliceu.movies.services.PermissionService;
import com.esliceu.movies.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private AutoritzationService autoritzationService;

    @Autowired
    private UserService userService; // Para obtener el usuario actual.

    @GetMapping("/request")
    public String requestPermissionForm(Model model) {
        model.addAttribute("permissions", permissionService.findAll());
        return "request-permission";
    }

    @PostMapping("/request")
    public String requestPermission(@RequestParam("permissionId") int permissionId, @RequestParam("userId") int userId) {
        User user = userService.findById(userId);
        Permission permission = permissionService.findById(permissionId);

        Autoritzation autoritzation = new Autoritzation();
        autoritzation.setUser(user);
        autoritzation.setPermission(permission);
        autoritzation.setState(Autoritzation.State.PENDING);

        autoritzationService.save(autoritzation);
        return "redirect:/?success";
    }
}
