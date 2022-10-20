package com.example.fullstackprojekt.repository;

import java.util.ArrayList;
import java.util.List;

public class WishRepository {

    // arrayListe der kommet fra database -> repository -> service -> controller
    private List<String> wishes= new ArrayList<>();

    public WishRepository(){
        wishes.add("Lars");
        wishes.add("Claus");
        wishes.add("Kaymås");
        wishes.add("Henrik");
    }

    public List<String> getAll() {
        return wishes;
    }
}

