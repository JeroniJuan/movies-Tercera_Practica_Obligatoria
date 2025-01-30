package com.esliceu.movies.controllers;

import com.esliceu.movies.models.Gender;
import com.esliceu.movies.services.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class GenderController {

    @Autowired
    private GenderService genderService;

    @GetMapping("/gender")
    public String listGenders(Model model) {
        model.addAttribute("genders", genderService.findAll());
        return "gender-list";
    }

    @GetMapping("/createGender")
    public String showCreateForm() {
        return "gender-create";
    }

    @PostMapping("/createGender")
    public String createGender(@ModelAttribute Gender gender) {
        genderService.save(gender);
        return "redirect:/gender";
    }

    @GetMapping("/genderDetail/{id}")
    public String showDetail(@PathVariable int id, Model model) {
        Gender gender = genderService.findById(id);
        model.addAttribute("gender", gender);
        return "gender-detail";
    }

    @PostMapping("/editGender")
    public String editGender(@ModelAttribute Gender gender) {
        genderService.save(gender);
        return "redirect:/gender";
    }

    @GetMapping("/deleteGender/{id}")
    public String deleteGender(@PathVariable int id) {
        genderService.deleteById(id);
        return "redirect:/gender";
    }
}
