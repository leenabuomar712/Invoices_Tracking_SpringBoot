package com.example.task2.task2.controllers.entityController;

import com.example.task2.task2.data.entities.User;
import com.example.task2.task2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/signup")
public class SignupController {
    @Autowired
    private UserService userService;

    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("User", new User());
        return "signup";
    }

    @PostMapping("/signup")
    public String signUp(@ModelAttribute("user") User user) {
        // Validate the user data and save it using the UserService
        userService.saveUser(user);
        return "redirect:/success"; // Redirect to the success page
    }
}
