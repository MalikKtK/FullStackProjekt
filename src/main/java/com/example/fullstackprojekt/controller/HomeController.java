package com.example.fullstackprojekt.controller;

import com.example.fullstackprojekt.model.Gift;
import com.example.fullstackprojekt.model.GiftList;
import com.example.fullstackprojekt.model.User;
import com.example.fullstackprojekt.repository.WishRepository;
import com.example.fullstackprojekt.service.WishListService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.Objects;

@Controller
public class HomeController {

    WishRepository repo = new WishRepository();
    WishListService service = new WishListService();

    @GetMapping("/")
    public String home() {
        return "home/index";
    }

    @GetMapping("/inspiration")
    public String inspiration() {
        return "home/inspiration";
    }

    @GetMapping("/about")
    public String about() {
        return "home/about";
    }

    @GetMapping("/contact")
    public String contact() {
        return "home/contact";
    }

    @GetMapping("/register")
    public String register() {
        return "user/register";
    }

    @GetMapping("/login")
    public String login() {
        return "user/login";
    }

    @PostMapping("/createUser")
    public String createUser(WebRequest dataFromForm, Model model) {
        String email = dataFromForm.getParameter("email");
        String userName = dataFromForm.getParameter("name");
        repo.createUser(new User(email, userName));

        model.addAttribute("email", email);

        return "redirect:/";
    }

    @GetMapping("/createWish")
    public String createWish(@RequestParam String email, Model model) {

        List<GiftList> wishLists = service.getAllWishlistsFromEmail(email);

        model.addAttribute("giftLists", wishLists);
        model.addAttribute("email", email);

        return "wish/createWish";
    }

    @GetMapping("/addProduct")
    public String addProduct(@RequestParam int listID, Model model) {

        List<Gift> gifts = service.getGiftsFromList(listID);
        GiftList giftList = service.getWishlist(listID);

        model.addAttribute("listName", giftList.getListName());
        model.addAttribute("newListID", listID);
        model.addAttribute("gifts", gifts);

        return "wish/addProduct";
    }

    @PostMapping("/addProduct/newProduct")
    public String newProduct(WebRequest dataFromForm) {

        String giftName = dataFromForm.getParameter("name");
        double giftPrice = Double.parseDouble(Objects.requireNonNull(dataFromForm.getParameter("price")));
        String url = dataFromForm.getParameter("url");

        int listID = Integer.parseInt(Objects.requireNonNull(dataFromForm.getParameter("newListID")));
        repo.createGift(new Gift(giftName, giftPrice, url), listID);


        return "redirect:/addProduct?listID=" + listID;
    }

    @PostMapping("/addProduct/deleteProduct")
    public String deleteProduct(WebRequest dataFromForm) {

        int giftID = Integer.parseInt(Objects.requireNonNull(dataFromForm.getParameter("giftID")));
        int listID = Integer.parseInt(Objects.requireNonNull(dataFromForm.getParameter("newListID")));

        repo.deleteGift(giftID);

        return "redirect:/addProduct?listID=" + listID;
    }

    @PostMapping("/createWish/addWishList")
    public String addWishList(WebRequest dataFromForm) {

        int listID = Integer.parseInt(Objects.requireNonNull(dataFromForm.getParameter("listID")));


        return "redirect:/addProduct?listID=" + listID;
    }

    @PostMapping("/createWish/newWishlist")
    public String newWishlist(WebRequest dataFromForm) {

        String listName = dataFromForm.getParameter("listName");
        String email = dataFromForm.getParameter("email");

        GiftList giftList = new GiftList(email, listName);

        repo.createWishlist(giftList);

        return "redirect:/createWish?email=" + email;
    }

    @PostMapping("/createWish/deleteWishlist")
    public String deleteWishlist(WebRequest dataFromForm) {

        int listID = Integer.parseInt(Objects.requireNonNull(dataFromForm.getParameter("listID")));
        String email = dataFromForm.getParameter("email");

        repo.deleteWishlist(listID);

        return "redirect:/createWish?email=" + email;
    }

    @GetMapping("/shareWishList")
    public String shareWishList(@RequestParam int listID, Model model) {

        List<Gift> gifts = service.getGiftsFromList(listID);
        GiftList giftList = service.getWishlist(listID);

        model.addAttribute("listName", giftList.getListName());
        model.addAttribute("newListID", listID);
        model.addAttribute("gifts", gifts);

        return "wish/shareWishList";
    }
}