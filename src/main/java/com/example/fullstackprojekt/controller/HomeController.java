package com.example.fullstackprojekt.controller;

import com.example.fullstackprojekt.repository.WishRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    WishRepository a = new WishRepository();


    @GetMapping("/")
    public String getSoldier(Model model){
        model.addAttribute("Wish1","a");
        model.addAttribute("Wish2", "b");
        model.addAttribute("Wish3", "c");

        return "index";
    }
}