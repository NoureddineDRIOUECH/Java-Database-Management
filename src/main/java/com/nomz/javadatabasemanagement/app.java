package com.nomz.javadatabasemanagement;

import com.nomz.javadatabasemanagement.dataaccessobject.ChowDB;
import com.nomz.javadatabasemanagement.dataaccessobject.DBconnection;
import com.nomz.javadatabasemanagement.dataaccessobject.DBcreation;
import com.nomz.javadatabasemanagement.dataaccessobject.DBdeletion;

import java.sql.Connection;
import java.util.Scanner;

public class app {
    public static void main(String[] args) {
    String dbName;
    System.out.print("Enter DB name: ");
    Scanner in = new Scanner(System.in);
    dbName = in.nextLine();
//    DBcreation.createDatabase(dbName);
//    Connection connection = DBconnection.getConnection(dbName);
//    if (connection == null) {
//        System.out.println("Failed to establish a connection.");
//    } else {
//        System.out.println("Connection established successfully.");
////    }
   DBdeletion.deleteDatabase(dbName);
    System.out.println("done");
}
}