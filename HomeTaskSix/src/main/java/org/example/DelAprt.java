package org.example;

import java.sql.*;
import java.util.Scanner;

public class DelAprt {
    public static void delAprt(Scanner scan, Connection con) throws SQLException {
        System.out.println("Please choose parameter to find elements for deletion:");

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

            System.out.println("Please enter data to find elements for deletion:");
            String sSearch = scan.nextLine();
            System.out.println();

            String columnName = md.getColumnName(sInt);
            try (PreparedStatement psSearch = con.prepareStatement("DELETE FROM Apartments WHERE " + columnName + " = ?")) {
                psSearch.setString(1, sSearch);
                if (psSearch.executeUpdate() < 1) {
                    System.out.println("Requested data has not been found and has not been deleted from the table 'Apartments'.\n");
                }
            }
        }
    }
}
