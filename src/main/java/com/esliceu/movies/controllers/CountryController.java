package com.esliceu.movies.controllers;

import com.esliceu.movies.models.Country;
import com.esliceu.movies.models.Permission;
import com.esliceu.movies.services.CountryService;
import com.esliceu.movies.services.PermissionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CountryController {

    @Autowired
    private CountryService countryService;

    @Autowired
    private PermissionService permissionService;

    @GetMapping("/country")
    public String listCountries(Model model) {
        model.addAttribute("countries", countryService.findAll());
        return "country-list";
    }

    @GetMapping("/searchCountry")
    public String searchCountry(@RequestParam String countryName, Model model) {
        Country country = countryService.findByName(countryName);

        if (country == null) {
            country = new Country();
            country.setCountryName(countryName);
            countryService.save(country);
        }

        model.addAttribute("country", country);
        return "country-detail";
    }

    @GetMapping("/createCountry")
    public String showCreateForm() {
        return "country-create";
    }

    @PostMapping("/createCountry")
    public String createCountry(@ModelAttribute Country country, HttpSession session) {
        int userId = (int) session.getAttribute("loggedInUserId");
        boolean canCreate = permissionService.isUserAuthorized(userId, Permission.permission_name.upload_movie);
        if (canCreate) countryService.save(country);
        return "redirect:/country";
    }

    @PostMapping("/editCountry")
    public String editCountry(@ModelAttribute Country country, HttpSession session) {
        int userId = (int) session.getAttribute("loggedInUserId");
        boolean canUpdate = permissionService.isUserAuthorized(userId, Permission.permission_name.modify_movie);
        if (canUpdate) countryService.save(country);
        return "redirect:/country";
    }

    @GetMapping("/deleteCountry/{id}")
    public String deleteCountry(@PathVariable int id, HttpSession session) {
        int userId = (int) session.getAttribute("loggedInUserId");
        boolean canDelete = permissionService.isUserAuthorized(userId, Permission.permission_name.remove_movie);
        if (canDelete) countryService.deleteById(id);
        return "redirect:/country";
    }
}
