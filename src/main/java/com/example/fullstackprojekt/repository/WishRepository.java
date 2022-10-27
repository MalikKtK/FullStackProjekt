package com.example.fullstackprojekt.repository;

import com.example.fullstackprojekt.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class WishRepository {
    private Connection con = DatabaseConnectionManager.getConn();

    private PreparedStatement psts;

    // ### user ###
    public void createUser(User user) {
        // return true or false if inserted

        try {
            psts = con.prepareStatement("INSERT INTO users VALUES (?,?)");

            // user parameters
            psts.setString(1, user.getEmail());
            psts.setString(2, user.getUserName());

            psts.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }

    }
}
