package com.nomz.javadatabasemanagement.dataaccessobject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBconnection {
    private static final String GREEN = "\u001B[32m";
    // ANSI escape code to reset color
    private static final String ANSI_RESET = "\u001B[0m";
    //Declaration des variables pour la connection
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 3306;
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static Connection connection;
    public static Connection getConnection(String dbName){
        try {
            connection = DriverManager.getConnection(String.format("jdbc:mysql://%s:%d/%s", HOST,PORT,dbName),USERNAME,PASSWORD);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return  connection;
    }
}
