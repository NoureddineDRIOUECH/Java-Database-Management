package com.nomz.javadatabasemanagement.dataaccessobject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class DBtablecreation {
    private static final String GREEN = "\u001B[32m";
    public static final String RESET = "\u001B[0m";

    public static void createTableAndInsertData( String dbName, String tableName, Scanner sc) {
        Connection con = DBconnection.getConnection(dbName);
        try {
            //on va sélectionner la base de donnée
            String useDatabaseSQL = "use " + dbName;
            try (PreparedStatement useDatabaseStatement = con.prepareStatement(useDatabaseSQL)) {
                useDatabaseStatement.executeUpdate();
            }

            // créer la table
            String createTableSQL = generateCreateTableSQL(tableName, sc);
            try (PreparedStatement createTableStatement = con.prepareStatement(createTableSQL)) {
                createTableStatement.executeUpdate();
                System.out.println(GREEN+"Table '" + tableName + "' créée avec succès."+RESET);

                // Demander à l'utilisateur d'entrer les données
                insertDataIntoTable(con, tableName, sc);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String generateCreateTableSQL(String tableName, Scanner sc) {
        StringBuilder createTableSQL = new StringBuilder("CREATE TABLE IF NOT EXISTS " + tableName + " (id INT AUTO_INCREMENT PRIMARY KEY");

        boolean continueInsertAttributes = true;

        while (continueInsertAttributes) {
            System.out.print("Entrez  l'attribut ( Entrer quitter pour terminer) : ");
            String attributeName = sc.nextLine().trim();

            if (attributeName.equals("quitter")) {
                continueInsertAttributes = false;
            } else {
                System.out.print("Entrez le type de l'attribut (int ou varchar) : ");
                String attributeType = sc.nextLine().trim();
                createTableSQL.append(", ").append(attributeName).append(" ").append(attributeType.equals("int") ? "INT" : "VARCHAR(50)");
            }
        }

        createTableSQL.append(")");

        return createTableSQL.toString();
    }

    public static void insertDataIntoTable(Connection con, String tableName, Scanner sc) throws SQLException {
        System.out.print("Combien de lignes voulez-vous insérer ? : ");
        int numRows;

        while (true) {
            try {
                numRows = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Veuillez entrer un nombre entier.");
            }
        }

        int i = 1;
        while (i <= numRows) {
            StringBuilder insertDataSQL = new StringBuilder("INSERT INTO " + tableName + " VALUES (DEFAULT");

            System.out.println("Entrez les données pour la ligne " + i + " :");

            if (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.trim().isEmpty()) {
                    break;
                }

                Scanner lineScanner = new Scanner(line);
                while (lineScanner.hasNext()) {
                    String value = lineScanner.next();
                    insertDataSQL.append(", '").append(value).append("'");
                }

                lineScanner.close();
                insertDataSQL.append(")");

                try (PreparedStatement insertDataStatement = con.prepareStatement(insertDataSQL.toString())) {
                    insertDataStatement.executeUpdate();
                }

                i++;
            } else {
                break;
            }
        }
        System.out.println(GREEN+"Vous avez remplissez le tableau Avec succès! "+RESET);
    }
}
