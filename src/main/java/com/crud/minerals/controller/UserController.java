package com.crud.minerals.controller;

import com.crud.minerals.domain.User;
import com.crud.minerals.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("registerRequest", new User());
        return "user_register";
    }

    @GetMapping("/login_u")
    public String getLoginPage(Model model) {
        model.addAttribute("loginRequest", new User());
        return "user_login";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user) {
        System.out.println("register request: " + user);
        User registeredUser = userService.registerUser(user.getLogin(), user.getPassword(), user.getEmail());
        return registeredUser == null ? "error" : "redirect:/login_u";
    }

    @PostMapping("/login_u")
    public String login(@ModelAttribute User user, Model model) {
        System.out.println("login request: " + user);
        User authenticatedUser = userService.authenticate(user.getLogin(), user.getPassword());
        if (authenticatedUser != null) {
            model.addAttribute("userLogin", authenticatedUser.getLogin());
            return "mineral_search";
        } else {
            return "error";
        }
    }
}
