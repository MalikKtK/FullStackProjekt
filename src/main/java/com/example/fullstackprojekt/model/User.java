package com.example.fullstackprojekt.model;

public record User(String email, String userName) {


    public String getEmail() {
        return email;
    }

    public String getUserName() {
        return userName;
    }

}
