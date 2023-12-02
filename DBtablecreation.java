package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class DBtablecreation {

    private Connection con;

    //  faisons la connexion.
    public DBtablecreation(Connection con) {
        this.con = con;
    }

    public void createTableAndInsertData(String dbName, String tableName, Scanner sc) {
        try {
            // Sélectionner la base de données
            String useDatabaseSQL = "use " + dbName;
            try (PreparedStatement useDatabaseStatement = con.prepareStatement(useDatabaseSQL)) {
                useDatabaseStatement.executeUpdate();
            }

            // Créer la table
            String createTableSQL = generateCreateTableSQL(tableName, sc);
            try (PreparedStatement createTableStatement = con.prepareStatement(createTableSQL)) {
                createTableStatement.executeUpdate();
                System.out.println("Table '" + tableName + "' créée avec succès.");

                // Demander à l'utilisateur d'entrer les données
                insertDataIntoTable(tableName, sc);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private String generateCreateTableSQL(String tableName, Scanner sc) {
        StringBuilder createTableSQL = new StringBuilder("CREATE TABLE IF NOT EXISTS " + tableName + " (id INT AUTO_INCREMENT PRIMARY KEY");

        while (true) {
            System.out.print("Entrez l'attribut (appuyez sur exit pour terminer) : ");
            String attributeName = sc.nextLine().trim();

            if (attributeName.equalsIgnoreCase("exit")) {
                break; // Sortir de la boucle si l'utilisateur entre "exit"
            }

            System.out.print("Entrez le type de l'attribut (int ou varchar) : ");
            String attributeType = sc.nextLine().trim();
            createTableSQL.append(", ").append(attributeName).append(" ").append(attributeType.equals("int") ? "INT" : "VARCHAR(50)");
        }

        createTableSQL.append(")");

        return createTableSQL.toString();
    }



    private void insertDataIntoTable(String tableName, Scanner sc) throws SQLException {
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
            System.out.println("Entrez les données pour la ligne " + i + " (ou tapez 'exit' pour terminer) :");

            if (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.trim().equalsIgnoreCase("exit")) {
                    System.out.println("Saisie terminée.");
                    break;
                }

                if (line.trim().isEmpty()) {
                    System.out.println("Veuillez entrer des données valides.");
                    continue;
                }

                StringBuilder insertDataSQL = new StringBuilder("INSERT INTO " + tableName + " VALUES (DEFAULT");

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

                System.out.println("Données de la ligne " + i + " insérées avec succès dans la table '" + tableName + "'.");
                i++;
            }
        }

        System.out.println("Données insérées avec succès dans la table '" + tableName + "'.");
    }
}
