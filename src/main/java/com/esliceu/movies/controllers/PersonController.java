package com.esliceu.movies.controllers;

import com.esliceu.movies.models.Person;
import com.esliceu.movies.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    // Mostrar todos los personas
    @GetMapping
    public String listPersons(Model model) {
        model.addAttribute("persons", personService.findAll());
        return "person-list";  // Este será el nombre del archivo HTML de la lista
    }

    // Ver una persona por su nombre
    @GetMapping("/search")
    public String searchPerson(@RequestParam String personName, Model model) {
        Person person = personService.findPersonByName(personName);

        if (person == null) {
            person = new Person();
            person.setPersonName(personName);
            personService.save(person);  // Crea uno nuevo si no se encuentra
        }

        model.addAttribute("person", person);
        return "person-detail";  // Este será el archivo HTML para mostrar y editar una persona
    }

    // Crear una nueva persona (en la página de detalle)
    @PostMapping("/create")
    public String createPerson(@ModelAttribute Person person) {
        personService.save(person);
        return "redirect:/person";
    }

    // Editar una persona
    @PostMapping("/edit")
    public String editPerson(@ModelAttribute Person person) {
        personService.save(person);
        return "redirect:/person";
    }

    // Eliminar una persona
    @GetMapping("/delete/{id}")
    public String deletePerson(@PathVariable int id) {
        personService.deleteById(id);
        return "redirect:/person";
    }
}
