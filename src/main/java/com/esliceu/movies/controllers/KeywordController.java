package com.esliceu.movies.controllers;

import com.esliceu.movies.models.Keyword;
import com.esliceu.movies.models.Permission;
import com.esliceu.movies.services.KeywordService;
import com.esliceu.movies.services.PermissionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class KeywordController {

    @Autowired
    private KeywordService keywordService;

    @Autowired
    private PermissionService permissionService;

    @GetMapping("/keyword")
    public String listKeywords(Model model) {
        model.addAttribute("keywords", keywordService.findAll());
        return "keyword-list";
    }

    @GetMapping("/searchKeyword")
    public String searchKeyword(@RequestParam String keywordName, Model model) {
        Keyword keyword = keywordService.findByName(keywordName);

        if (keyword == null) {
            keyword = new Keyword();
            keyword.setKeywordName(keywordName);
            keywordService.save(keyword);
        }

        model.addAttribute("keyword", keyword);
        return "keyword-detail";
    }

    @GetMapping("/createKeyword")
    public String showCreateForm() {
        return "keyword-create";
    }

    @PostMapping("/createKeyword")
    public String createKeyword(@ModelAttribute Keyword keyword, HttpSession session) {
        int userId = (int) session.getAttribute("loggedInUserId");
        boolean canCreate = permissionService.isUserAuthorized(userId, Permission.permission_name.upload_movie);
        if (canCreate) keywordService.save(keyword);
        return "redirect:/keyword";
    }

    @PostMapping("/editKeyword")
    public String editKeyword(@ModelAttribute Keyword keyword, HttpSession session) {
        int userId = (int) session.getAttribute("loggedInUserId");
        boolean canUpdate = permissionService.isUserAuthorized(userId, Permission.permission_name.modify_movie);
        if (canUpdate) keywordService.save(keyword);
        return "redirect:/keyword";
    }

    @GetMapping("/deleteKeyword/{id}")
    public String deleteKeyword(@PathVariable int id, HttpSession session) {
        int userId = (int) session.getAttribute("loggedInUserId");
        boolean canDelete = permissionService.isUserAuthorized(userId, Permission.permission_name.remove_movie);
        if (canDelete) keywordService.deleteById(id);
        return "redirect:/keyword";
    }
}
