package com.example.demo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/app")
public class UserController {
    // public endpoint, no auth req
    @GetMapping("/public/hello")
    public String publicHello(){
        return "publicHello"; // view
    }
    // user only
    @GetMapping("/user/hello")
    public String userHello(){
        return "userHello";
    }
// admin only
    @GetMapping("/admin/hello")
    public String adminHello(){
        return "adminHello";
    }
    // custom
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    // custom logout
    @GetMapping("/logout")
    public String logout(){
        return "logout";
    }




}
