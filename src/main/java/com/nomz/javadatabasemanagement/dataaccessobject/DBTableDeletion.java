package com.nomz.javadatabasemanagement.dataaccessobject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBTableDeletion {
    public static void dropTable(String dbName, String tableName) {
        Connection con = DBconnection.getConnection(dbName);
        try {
            String dropTableSQL = "DROP TABLE IF EXISTS " + tableName;

            try (PreparedStatement dropTableStatement = con.prepareStatement(dropTableSQL)) {
                dropTableStatement.executeUpdate();
                System.out.println("Table '" + tableName + "' supprimée avec succès.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
