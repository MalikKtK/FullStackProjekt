package com.example.fullstackprojekt.controller;

import com.example.fullstackprojekt.model.User;
import com.example.fullstackprojekt.repository.WishRepository;
import com.example.fullstackprojekt.service.WishlistService;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@Controller
public class HomeController {

    WishRepository a = new WishRepository();
    WishlistService service = new WishlistService();


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

    @PostMapping("/createUser")
    public String createUser(WebRequest dataFromForm, Model model) {
        String email = dataFromForm.getParameter("email");
        String userName = dataFromForm.getParameter("name");
        a.createUser(new User(email, userName));

        model.addAttribute("email", email);

        return "redirect:/";
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