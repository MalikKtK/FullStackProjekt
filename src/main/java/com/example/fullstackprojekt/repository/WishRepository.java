package com.example.fullstackprojekt.repository;

import com.example.fullstackprojekt.model.Gift;
import com.example.fullstackprojekt.model.GiftList;
import com.example.fullstackprojekt.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class WishRepository {
    private final Connection conn = DatabaseConnectionManager.getConn();

    private PreparedStatement psts;

    public void createUser(User user) {

        try {
            psts = conn.prepareStatement("INSERT INTO users VALUES (?,?)");

            psts.setString(1, user.getEmail());
            psts.setString(2, user.getUserName());

            psts.executeUpdate();

        } catch (SQLException ignored) {
        }

    }

    public void createWishlist(GiftList giftList) {
        PreparedStatement psts;

        try {
            psts = conn.prepareStatement("INSERT INTO wishlists (listID, email, listName) VALUES (?, ?, ?);");
            psts.setInt(1, giftList.getListID());
            psts.setString(2, giftList.getEmail());
            psts.setString(3, giftList.getListName());

            psts.executeUpdate();

        } catch (SQLException ignored) {
        }

    }

    public void deleteWishlist(int wishListID) {
        try {
            psts = conn.prepareStatement("DELETE FROM wishlists WHERE listID = ?;");
            psts.setInt(1, wishListID);

            psts.executeUpdate();

        } catch (SQLException ignored) {
        }

    }


    public void createGift(Gift gift, int listID) {

        PreparedStatement psts;

        try {
            psts = conn.prepareStatement("INSERT INTO gifts (giftID, listID, giftName, price, url, isReserved) VALUES (?,?,?,?,?,?);");
            psts.setInt(1, gift.getGiftID());
            psts.setInt(2, listID);
            psts.setString(3, gift.getGiftName());
            psts.setDouble(4, gift.getPrice());
            psts.setString(5, gift.getUrl());
            psts.executeUpdate();
        } catch (SQLException ignored) {
        }

    }


    public void deleteGift(int giftID) {
        try {
            psts = conn.prepareStatement("DELETE FROM gifts WHERE giftID = ?;");
            psts.setInt(1, giftID);
            psts.executeUpdate();

        } catch (SQLException ignored) {
        }

    }
}