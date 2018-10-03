package com.shadowsecure.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import controllers.DialogController;

public class DBConnect {

    private static DBConnect instance = new DBConnect();
    public static final String url = "jdbc:mysql://localhost:3306/shadow_secure";
    public static final String userNameDB = "root";
    public static final String passwordDB = "root";
    public static final String Driver = "com.mysql.jdbc.Driver";

    private DBConnect() {

        try {

            Class.forName(Driver);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private Connection createConnection() {

        Connection connection = null;

        try {

            connection = DriverManager.getConnection(url, userNameDB, passwordDB);
            
        } catch (SQLException e) {
            e.printStackTrace();
            DialogController.showConnectionError();
        }
  
        return connection;
        
    }

    public static Connection getConnection() {

        return instance.createConnection();

    }

}
