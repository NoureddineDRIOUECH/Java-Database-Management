package com.nomz.javadatabasemanagement.dataaccessobject;
import java.util.InputMismatchException;
import java.util.Scanner;

import static com.nomz.javadatabasemanagement.dataaccessobject.DBShow.showTable;
import static com.nomz.javadatabasemanagement.dataaccessobject.DBdeletion.deleteDatabase;


public class MenuOptions {
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String BLUE = "\u001B[34m";
    private static final Scanner scanner = new Scanner(System.in);

    public static void Menuprincipal() {
        boolean choixValide = false;
        while (!choixValide) {
            try {
                System.out.println(RED + "Choisissez parmi les options suivantes :");
                System.out.println(BLUE + "1. Création d'un base de données ");
                System.out.println("2. Afficher un tableau de votre base de données ");
                System.out.println("3. Modifier la base de données ");
                System.out.println("4. Supprimer une base de données ");
                System.out.println("5. Quitter le programme " + RESET);

                System.out.print("Entrez le numéro de votre choix : ");
                int choix = scanner.nextInt();
                scanner.nextLine();

                switch (choix) {
                    case 1:
                        System.out.println("Entrer un nom pour votre base de données : ");
                        String DbName = scanner.nextLine();
                        DBcreation.createDatabase(DbName);
                        Menuprincipal();
                        break;
                    case 2:
                        System.out.println("Entrer le nom de votre base de données : ");
                        String DbName1 = scanner.nextLine();
                        System.out.println("Entrer le nom de votre tableau que vous souhaitez afficher : ");
                        String TableName = scanner.nextLine();
                        showTable(DbName1,TableName);
                        Menuprincipal();
                        break;
                    case 3:
                        Menusecondaire1();
                        break;
                    case 4:
                        System.out.println("Entrer le nom de base de données que vous souhaitez supprimer : ");
                        String DbName2 = scanner.nextLine();
                        deleteDatabase(DbName2);
                        Menuprincipal();
                        break;
                    case 5:
                        System.out.println(RED + "Le programme est terminé !!" +RESET);
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Choix invalide");
                        System.out.println("Veuillez entrer un nombre valide.");
                        Menuprincipal();
                        break;
                }
                choixValide = true;
            } catch (InputMismatchException e) {
                System.out.println("Veuillez entrer un nombre valide.");
                scanner.next();
            }
        }
    }

    public static void Menusecondaire1() {
        boolean choixValide = false;
        while (!choixValide) {
            try {
                System.out.println(RED + "Choisissez parmi les options suivantes :");
                System.out.println(BLUE + "1. Créer un tableau et remplissez-le ");
                System.out.println("2. Supprimer un tableau ");
                System.out.println("3. Revenir à la menu précédente " + RESET);

                System.out.print("Entrez le numéro de votre choix : ");
                int choix = scanner.nextInt();
                scanner.nextLine();

                switch (choix) {
                    case 1:
                        Scanner sc = new Scanner(System.in);
                        System.out.println("Entrer le nom de votre base de données : ");
                        String DBName = sc.nextLine();
                        System.out.println("Entrer le nom de votre tableau que vous souhaitez crée : ");
                        String TableName = sc.nextLine();
                        DBtablecreation.createTableAndInsertData(DBName, TableName, sc);
                        Menusecondaire1();
                        break;
                    case 2:
                        Scanner sc1 = new Scanner(System.in);
                        System.out.println("Entrer le nom de votre base de données : ");
                        String DBName1 = sc1.nextLine();
                        System.out.println("Entrer le nom de tableau que vous souhaitez supprimer : ");
                        String TableName1 = sc1.nextLine();
                        DBTableDeletion.dropTable(DBName1, TableName1);
                        Menusecondaire1();
                        break;
                    case 3:
                        Menuprincipal();
                        break;
                    default:
                        System.out.println("Choix invalide");
                        System.out.println("Veuillez entrer un nombre valide.");
                        Menusecondaire1();
                        break;
                }
                choixValide = true;
            } catch (InputMismatchException e) {
                System.out.println("Veuillez entrer un nombre valide.");
                scanner.next();
            }
        }
    }

    public static void main(String[] args) {

    }
}