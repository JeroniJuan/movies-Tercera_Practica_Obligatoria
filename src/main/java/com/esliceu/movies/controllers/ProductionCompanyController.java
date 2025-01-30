package com.esliceu.movies.controllers;

import com.esliceu.movies.models.Production_Company;
import com.esliceu.movies.services.ProductionCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductionCompanyController {

    @Autowired
    private ProductionCompanyService productionCompanyService;

    @GetMapping("/productionCompany")
    public String listProductionCompanies(Model model) {
        model.addAttribute("productionCompanies", productionCompanyService.findAll());
        return "productionCompany-list";
    }

    @GetMapping("/createProductionCompany")
    public String showCreateForm() {
        return "productionCompany-create";
    }

    @PostMapping("/createProductionCompany")
    public String createProductionCompany(@ModelAttribute Production_Company productionCompany) {
        productionCompanyService.save(productionCompany);
        return "redirect:/productionCompany";
    }

    @GetMapping("/productionCompanyDetail/{id}")
    public String showDetail(@PathVariable int id, Model model) {
        Production_Company productionCompany = productionCompanyService.findById(id);
        model.addAttribute("productionCompany", productionCompany);
        return "productionCompany-detail";
    }

    @PostMapping("/editProductionCompany")
    public String editProductionCompany(@ModelAttribute Production_Company productionCompany) {
        productionCompanyService.save(productionCompany);
        return "redirect:/productionCompany";
    }

    @GetMapping("/deleteProductionCompany/{id}")
    public String deleteProductionCompany(@PathVariable int id) {
        productionCompanyService.deleteById(id);
        return "redirect:/productionCompany";
    }
}
