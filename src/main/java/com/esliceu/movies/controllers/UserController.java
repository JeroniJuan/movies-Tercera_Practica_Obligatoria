package com.esliceu.movies.controllers;

import com.esliceu.movies.models.User;
import com.esliceu.movies.services.UserService;
import com.esliceu.movies.utils.StringToSHA256;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/register")
    public String getRegisterPage() {
        return "register";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password, @RequestParam String mail) {
        try {
            User existingUser = userService.findByEmail(mail);
            if (existingUser != null) {
                return "register?error=email_exists";
            }
            User user = new User(username, password, mail);

            userService.save(user);

            return "redirect:/";
        } catch (NoSuchAlgorithmException e) {
            return "register";
        } catch (Exception e) {
            return "register";
        }
    }


    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session) throws NoSuchAlgorithmException {
        String hashedPassword = StringToSHA256.getSHA256(password);

        User user = userService.findByUserName(username);

        if (user != null && userService.checkPassword(user, password)) {
            session.setAttribute("loggedInUser", user.getUserName());
            return "redirect:/";
        } else {
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
