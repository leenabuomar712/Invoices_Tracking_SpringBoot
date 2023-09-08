package com.example.task2.task2.controllers.entityController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/signup")
public class AppController {
    @GetMapping("/success")
    public String showSuccessPage() {
        return "success"; // Return the success page template
    }
}
