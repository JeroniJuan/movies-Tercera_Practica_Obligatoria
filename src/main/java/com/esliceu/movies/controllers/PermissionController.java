package com.esliceu.movies.controllers;

import com.esliceu.movies.models.Autoritzation;
import com.esliceu.movies.models.Permission;
import com.esliceu.movies.models.User;
import com.esliceu.movies.services.AutoritzationService;
import com.esliceu.movies.services.PermissionService;
import com.esliceu.movies.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private AutoritzationService autoritzationService;

    @Autowired
    private UserService userService;

    @GetMapping("/request")
    public String requestPermissionForm(Model model, HttpSession session) {
        int userId = (int) session.getAttribute("loggedInUserId");
        String userRole = userService.findById(userId).getRole();
        model.addAttribute("userId", userId);
        model.addAttribute("userRole", userRole);
        model.addAttribute("permissions", permissionService.findAll());

        if (userId == 2) {
            List<Autoritzation> pendingRequests = autoritzationService.findPendingRequests();
            model.addAttribute("pendingRequests", pendingRequests);
        }

        return "request-permission";
    }

    @PostMapping("/request")
    public String requestPermission(@RequestParam("permissionId") int permissionId, @RequestParam("userId") int userId) {
        User user = userService.findById(userId);
        Autoritzation autoritzation = new Autoritzation();
        autoritzation.setUser(user);
        autoritzation.setPermission(permissionService.findById(permissionId));
        autoritzation.setState(Autoritzation.State.PENDING);

        autoritzationService.save(autoritzation);
        return "redirect:/?success";
    }

    @PostMapping("/manage-requests")
    public String updateRequest(@RequestParam("requestId") int requestId, @RequestParam("action") String action, HttpSession session) {
        Autoritzation autoritzation = autoritzationService.findById(requestId);
        int userId = (int) session.getAttribute("loggedInUserId");
        User user = userService.findById(userId);
        if (user.getRole().equals("Admin")){
            if ("accept".equals(action)) {
                autoritzation.setState(Autoritzation.State.ACCEPTED);
            } else if ("reject".equals(action)) {
                autoritzation.setState(Autoritzation.State.REJECTED);
            }
            autoritzationService.save(autoritzation);
        }
        return "redirect:/request";
    }
    @GetMapping("/checkPermissions")
    public String getPermissions(HttpSession session, Model model){
        int userId = (int) session.getAttribute("loggedInUserId");
        User user = userService.findById(userId);
        List<Autoritzation> permissionList = autoritzationService.findByUserId(userId);
        model.addAttribute("permissionList", permissionList);
        return "user-permissions";
    }
}
