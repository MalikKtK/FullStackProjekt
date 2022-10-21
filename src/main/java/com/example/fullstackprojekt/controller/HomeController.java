package com.example.fullstackprojekt.controller;

import com.example.fullstackprojekt.repository.WishRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    WishRepository a = new WishRepository();


    @GetMapping("/inspiration")
    public String inspiration() {
        return "inspiration";
    }
    @GetMapping("/about")
    public String about() {
        return "about";
    }
    @GetMapping("/help")
    public String help() {
        return "help";
    }
    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String getWish(Model model){
        model.addAttribute("Wish1","a");
        model.addAttribute("Wish2", "b");
        model.addAttribute("Wish3", "c");
        return "index";
    }
}