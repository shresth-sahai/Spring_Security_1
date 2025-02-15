package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app")
public class UserController {

    // Public endpoint, no authentication required
    @GetMapping("/public/hello")
    public String publicHello() {
        return "publicHello"; // Returns a view (publicHello.html)
    }

    // User-only accessible endpoint
    @GetMapping("/user/hello")
    public String userHello() {
        return "userHello"; // Returns a view (userHello.html)
    }

    // Admin-only accessible endpoint
    @GetMapping("/admin/hello")
    public String adminHello() {
        return "adminHello"; // Returns a view (adminHello.html)
    }

    // Custom login page
    @GetMapping("/login")
    public String login() {
        return "login"; // Returns the login view (login.html)
    }

    // Logout endpoint (handled automatically by Spring Security)
    @GetMapping("/logout")
    public String logout() {
        return "logout"; // Redirects to the login page after logout (handled by Spring Security)
    }
}
