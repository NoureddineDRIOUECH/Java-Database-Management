package com.nomz.javadatabasemanagement.dataaccessobject;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBdeletion {
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";
    public static void deleteDatabase(String dbname){

        try {
            Connection connection = DBconnection.getConnection(dbname);
            Statement statement = connection.createStatement();
            String deleteDBQuery = String.format("DROP DATABASE IF EXISTS %s", dbname);
            statement.executeUpdate(deleteDBQuery);
            System.out.println(ANSI_RED + "Database deleted successfully." + ANSI_RESET);
            statement.close();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}
