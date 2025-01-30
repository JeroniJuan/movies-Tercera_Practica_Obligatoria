package com.esliceu.movies.controllers;

import com.esliceu.movies.models.Permission;
import com.esliceu.movies.models.Person;
import com.esliceu.movies.services.PermissionService;
import com.esliceu.movies.services.PersonService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PersonController {

    @Autowired
    private PersonService personService;

    @Autowired
    private PermissionService permissionService;

    @GetMapping("/person")
    public String listPersons(Model model) {
        model.addAttribute("persons", personService.findAll());
        return "person-list";
    }

    @GetMapping("/searchPerson")
    public String searchPerson(@RequestParam String personName, Model model) {
        Person person = personService.findPersonByName(personName);

        if (person == null) {
            person = new Person();
            person.setPersonName(personName);
            personService.save(person);
        }

        model.addAttribute("person", person);
        return "person-detail";
    }

    @GetMapping("/createPerson")
    public String showCreateForm() {
        return "person-create";
    }

    @PostMapping("/createPerson")
    public String createPerson(@ModelAttribute Person person, HttpSession session) {
        int userId = (int) session.getAttribute("loggedInUserId");
        boolean canCreate = permissionService.isUserAuthorized(userId, Permission.permission_name.upload_movie);
        if (canCreate) personService.save(person);
        return "redirect:/person";
    }

    @PostMapping("/editPerson")
    public String editPerson(@ModelAttribute Person person, HttpSession session) {
        int userId = (int) session.getAttribute("loggedInUserId");
        boolean canUpdate = permissionService.isUserAuthorized(userId, Permission.permission_name.modify_movie);
        if (canUpdate) personService.save(person);
        return "redirect:/person";
    }

    @GetMapping("/deletePerson/{id}")
    public String deletePerson(@PathVariable int id, HttpSession session) {
        int userId = (int) session.getAttribute("loggedInUserId");
        boolean canDelete = permissionService.isUserAuthorized(userId, Permission.permission_name.remove_movie);
        if (canDelete) personService.deleteById(id);
        return "redirect:/person";
    }
}
