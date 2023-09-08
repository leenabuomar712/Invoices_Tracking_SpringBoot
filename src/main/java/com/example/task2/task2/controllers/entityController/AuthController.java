package com.example.task2.task2.controllers.entityController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login() {
        return "redirect:/dashboard";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }
}
