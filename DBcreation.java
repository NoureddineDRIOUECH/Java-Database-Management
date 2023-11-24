package com.nomz.javadatabasemanagement.dataaccessobject;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBcreation {
    private static final String ANSI_GREEN = "\u001B[32m";
    // ANSI escape code to reset color
    private static final String ANSI_RESET = "\u001B[0m";
    public static void createDatabase(String dbName) {
        try {
            Connection connection = DBconnection.getConnection("");
            Statement statement = connection.createStatement();

            // Création de la base de données
            String createDBQuery = String.format("CREATE DATABASE IF NOT EXISTS %s", dbName);
            statement.executeUpdate(createDBQuery);

            System.out.println(ANSI_GREEN + "Database created successfully." + ANSI_RESET);

            // Fermeture des ressources
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
