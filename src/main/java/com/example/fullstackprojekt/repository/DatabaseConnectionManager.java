package com.example.fullstackprojekt.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionManager {

    public static Connection getConn() {
        String hostname = "jdbc:mysql://keadatabase.mysql.database.azure.com/wishlist";
        String username = "malik2635";
        String password = "Nwr92auv";

        Connection conn;
        try {
            conn = DriverManager.getConnection(hostname, username, password);
        } catch (SQLException e) {
            System.out.println("Kan ikke forbinde til database");
            throw new RuntimeException(e);
        }
        System.out.println("Forbindelse til database OK!");
        return conn;
    }
}
