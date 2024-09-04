package com.example.m2l;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public static Connection getConnection() throws SQLException {
        try {
            // Charge le pilote JDBC MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("MySQL JDBC drigetConnectionver not found", e);
        }
        // Retourne la connexion établie à la base de données
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/m2l", "root", "");
    }
}


