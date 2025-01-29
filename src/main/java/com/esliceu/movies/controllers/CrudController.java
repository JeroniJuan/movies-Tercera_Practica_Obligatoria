package com.esliceu.movies.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CrudController {
    @GetMapping("/crud")
    public String getCrud(Model model){
        return "crud";
    };
}