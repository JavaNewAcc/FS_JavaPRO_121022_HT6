package org.example;

import com.mysql.cj.xdevapi.DatabaseObject;

import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class Main {
    static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/FlatDB?serverTimezone=Europe/Kiev";
    static final String DB_USERNAME = "root";
    static final String DB_PASSWORD = "password";

    static Connection con;

    public static void main(String[] args) {
        try (Scanner scan = new Scanner(System.in)) {
            con = DriverManager.getConnection(DB_CONNECTION, DB_USERNAME, DB_PASSWORD);
            CreateDB.initDB(con);

            while (true) {
                System.out.println("1: Add apartment");
                System.out.println("2: Delete apartment");
                System.out.println("3: Edit apartment");
                System.out.println("4: Find apartment");
                System.out.println("5: View all apartments");
                System.out.print("Please choose menu item: ");

                String s = scan.nextLine();
                System.out.println();
                switch (s) {
                    case "1" -> AddAprt.addAprt(scan, con);
                    case "2" -> DelAprt.delAprt(scan, con);
                    case "3" -> EditAprt.editAprt(scan, con);
                    case "4" -> FindAprt.findAprt(scan, con);
                    case "5" -> ViewAprt.viewAprt(con);
                    default -> {
                        return;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
