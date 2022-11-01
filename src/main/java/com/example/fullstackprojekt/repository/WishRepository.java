package com.example.fullstackprojekt.repository;

import com.example.fullstackprojekt.model.Gift;
import com.example.fullstackprojekt.model.GiftList;
import com.example.fullstackprojekt.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public GiftList getWishlist(int wishListID) {
        GiftList giftList = null;

        try {
            psts = conn.prepareStatement("SELECT * FROM wishlists WHERE listID = ?;");
            psts.setInt(1, wishListID);
            ResultSet resultSet = psts.executeQuery();

            while (resultSet.next()) {
                giftList = new GiftList(
                        resultSet.getInt("listID"),
                        resultSet.getString("email"),
                        resultSet.getString("listName"));
            }

        } catch (SQLException ignored) {
        }

        return giftList;
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
            psts.setBoolean(6, gift.getReserved());

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

    public List<Gift> getGiftsFromList(int listID) {
        ArrayList<Gift> gifts = new ArrayList<>();

        try {
            psts = conn.prepareStatement("SELECT * FROM gifts WHERE listID = ?;");
            psts.setInt(1, listID);
            ResultSet resultSet = psts.executeQuery();

            while (resultSet.next()) {
                gifts.add(new Gift(
                        resultSet.getInt("giftID"),
                        resultSet.getString("giftName"),
                        resultSet.getDouble("price"),
                        resultSet.getString("url"),
                        resultSet.getBoolean("isReserved")
                ));
            }

        } catch (SQLException ignored) {
        }

        return gifts;
    }

    public void setIsReservedForGift(int giftID, boolean isReserved) {
        try {
            psts = conn.prepareStatement(
                    "UPDATE gifts SET isReserved = ? WHERE giftID = ?;");
            psts.setBoolean(1, isReserved);
            psts.setInt(2, giftID);
            psts.executeUpdate();

        } catch (SQLException ignored) {
        }

    }

    public List<GiftList> getAllWishlistsFromEmail(String email) {
        List<GiftList> giftLists = new ArrayList<>();
        try {
            psts = conn.prepareStatement("SELECT * FROM wishlists WHERE email = ?");
            psts.setString(1, email);
            ResultSet resultset = psts.executeQuery();

            while (resultset.next()) {
                giftLists.add(new GiftList(
                        resultset.getInt("listID"),
                        resultset.getString("email"),
                        resultset.getString("listName")
                ));
            }

        } catch (Exception ignored) {
        }
        return giftLists;

    }

}
