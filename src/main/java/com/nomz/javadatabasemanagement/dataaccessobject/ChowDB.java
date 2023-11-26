package com.nomz.javadatabasemanagement.dataaccessobject;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ChowDB {
    public static void chowDB (String dbname){
        Connection connection = DBconnection.getConnection(dbname);
        try (Statement statement = connection.createStatement()) {
            String queryChow  = "SHOW TABLES";

            System.out.println(statement.executeUpdate(queryChow));
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
