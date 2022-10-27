package com.example.fullstackprojekt.service;

import com.example.fullstackprojekt.model.User;
import com.example.fullstackprojekt.repository.WishRepository;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

public class WishlistService {

    WishRepository repo = new WishRepository();

    public void createUser(WebRequest request) {

        request.getParameter("email");
        request.getParameter("name");

        User user = new User(request.getParameter("email"),
                request.getParameter("name"));

        repo.createUser(user);
    }

}
