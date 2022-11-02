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

    @GetMapping("/help")
    public String help() {
        return "home/help";
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

    @GetMapping("/giftList")
    public String myWishlists(@RequestParam String email, Model model) {

        List<GiftList> wishLists = service.getAllWishlistsFromEmail(email);

        model.addAttribute("giftLists", wishLists);
        model.addAttribute("email", email);

        return "wish/giftList";
    }

    @GetMapping("/gifts")
    public String myGifts(@RequestParam int listID, Model model) {

        List<Gift> gifts = service.getGiftsFromList(listID);
        GiftList giftList = service.getWishlist(listID);

        model.addAttribute("listName", giftList.getListName());
        model.addAttribute("newListID", listID);
        model.addAttribute("gifts", gifts);

        return "wish/gifts";
    }

    @PostMapping("/gifts/newGift")
    public String newGift(WebRequest dataFromForm) {

        String giftName = dataFromForm.getParameter("name");
        double giftPrice = Double.parseDouble(Objects.requireNonNull(dataFromForm.getParameter("price")));
        String url = dataFromForm.getParameter("url");

        int listID = Integer.parseInt(Objects.requireNonNull(dataFromForm.getParameter("newListID")));
        repo.createGift(new Gift(giftName, giftPrice, url, false), listID);


        return "redirect:/gifts?listID=" + listID;
    }

    @PostMapping("/gifts/deleteGift")
    public String deleteGift(WebRequest dataFromForm) {

        int giftID = Integer.parseInt(Objects.requireNonNull(dataFromForm.getParameter("giftID")));
        int listID = Integer.parseInt(Objects.requireNonNull(dataFromForm.getParameter("newListID")));

        repo.deleteGift(giftID);

        return "redirect:/gifts?listID=" + listID;
    }

    @PostMapping("/giftList/editList")
    public String editList(WebRequest dataFromForm) {

        int listID = Integer.parseInt(Objects.requireNonNull(dataFromForm.getParameter("listID")));


        return "redirect:/gifts?listID=" + listID;
    }

    @PostMapping("/giftList/newWishlist")
    public String newWishlist(WebRequest dataFromForm) {

        String listName = dataFromForm.getParameter("listName");
        String email = dataFromForm.getParameter("email");

        GiftList giftList = new GiftList(email, listName);

        repo.createWishlist(giftList);

        return "redirect:/giftList?email=" + email;
    }

    @PostMapping("/giftList/deleteWishlist")
    public String deleteWishlist(WebRequest dataFromForm) {

        int listID = Integer.parseInt(Objects.requireNonNull(dataFromForm.getParameter("listID")));
        String email = dataFromForm.getParameter("email");

        repo.deleteWishlist(listID);

        return "redirect:/giftList?email=" + email;
    }

    @GetMapping("/shareGifts")
    public String shareGift(@RequestParam int listID, Model model) {

        List<Gift> gifts = service.getGiftsFromList(listID);
        GiftList giftList = service.getWishlist(listID);

        model.addAttribute("listName", giftList.getListName());
        model.addAttribute("newListID", listID);
        model.addAttribute("gifts", gifts);

        return "wish/shareGifts";
    }

    @PostMapping("/shareGifts/update")
    public String updateReserved(WebRequest dataFromForm) {
        int listID = Integer.parseInt(Objects.requireNonNull(dataFromForm.getParameter("newListID")));

        int giftID = Integer.parseInt(Objects.requireNonNull(dataFromForm.getParameter("giftID")));
        String strIsReserved = dataFromForm.getParameter("reserved");

        boolean isReserved;
        isReserved = strIsReserved != null;


        service.setIsReservedForGift(giftID, isReserved);


        return "redirect:/shareGifts?listID=" + listID;
    }
}