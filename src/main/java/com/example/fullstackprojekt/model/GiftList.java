package com.example.fullstackprojekt.model;

public class GiftList {
    private int listID;
    private String email;
    private String listName;

    public GiftList(int listID, String email, String listName) {
        setListID(listID);
        setEmail(email);
        setListName(listName);
    }

    public GiftList(String email, String listName) {
        setEmail(email);
        setListName(listName);
    }

    public int getListID() {
        return listID;
    }

    public void setListID(int listID) {
        this.listID = listID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    @Override
    public String toString() {
        return "GiftList{" +
                "listID=" + listID +
                ", email='" + email + '\'' +
                ", listName='" + listName + '\'' +
                '}';
    }
}
