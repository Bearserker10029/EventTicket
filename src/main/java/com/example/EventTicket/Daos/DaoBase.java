package com.example.EventTicket.Daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoBase {

    public Connection getConection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        String username = "root";
        String password = "root";
        String database = "lab10_eventos";
        String url = "jdbc:mysql://localhost:3306/" + database;

        return DriverManager.getConnection(url, username, password);

    }
}
