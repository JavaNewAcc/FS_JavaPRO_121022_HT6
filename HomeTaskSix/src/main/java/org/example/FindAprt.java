package org.example;

import java.sql.*;
import java.util.Scanner;

public class FindAprt {
    public static void findAprt(Scanner scan, Connection con) throws SQLException {

        System.out.println("Please choose parameter that will be used for search:");

        Statement st = con.createStatement();
        try (ResultSet rs = st.executeQuery("SELECT * FROM Apartments");) {
            ResultSetMetaData md = rs.getMetaData();
            for (int i = 1; i <= md.getColumnCount(); i++) {
                System.out.println(i + ") " + md.getColumnName(i));
            }
            System.out.println("-> ");
            String s = scan.nextLine();

            int sInt = 0;
            try {
                sInt = Integer.parseInt(s);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }

            System.out.println("Please enter data for search:");
            String sSearch = scan.nextLine();
            String columnName = md.getColumnName(sInt);
            try (PreparedStatement psSearch = con.prepareStatement("SELECT * FROM Apartments WHERE " + columnName + " = ?")) {
                psSearch.setString(1, sSearch);

                try (ResultSet rsSearch = psSearch.executeQuery()) {
                    int rowCounter = 0;
                    ResultSetMetaData mdSearch = rsSearch.getMetaData();
                    for (int i = 1; i <= mdSearch.getColumnCount(); i++) {
                        System.out.print(mdSearch.getColumnName(i) + "\t\t");
                    }
                    System.out.println();
                    while (rsSearch.next()) {
                        for (int i = 1; i <= mdSearch.getColumnCount(); i++) {
                            System.out.print(rsSearch.getString(i) + "\t\t");
                        }
                        rowCounter++;
                        System.out.println();
                    }
                    System.out.println();
                    if (rowCounter == 0) {
                        System.out.println("No data was found \n");
                    }
                }
            }
        }
    }
}
