package com.nomz.javadatabasemanagement.dataaccessobject;

import java.sql.*;

import static com.nomz.javadatabasemanagement.dataaccessobject.DBconnection.getConnection;


public class DBShow {

    public static ResultSet showTable(String DBName, String TableName) {
        ResultSet resultSet = null;
        try {
            Statement statement = getConnection(DBName).createStatement();
            resultSet = statement.executeQuery("SELECT * FROM "+TableName);

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    String columnValue = resultSet.getString(i);
                    System.out.println(columnName + ": " + columnValue);
                }
                System.out.println("---------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

}
