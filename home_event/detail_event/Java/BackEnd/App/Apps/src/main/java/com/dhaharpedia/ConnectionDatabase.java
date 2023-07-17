package com.dhaharpedia;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

abstract class ConnectionDatabase {
    private String DB_URL = "jdbc:mysql://localhost/Dhaharpedia";
    private String USER = "root";
    private String PASS = ""; 

    protected Connection getConnection(){
        try {
            return DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    };
    protected abstract void getData(); 
   
    
}