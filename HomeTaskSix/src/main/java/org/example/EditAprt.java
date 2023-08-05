package org.example;

import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class EditAprt {
    public static void editAprt(Scanner scan, Connection con) throws SQLException {
        System.out.println("Please enter apartment ID number to edit apartment data:");
        String sSearch = scan.nextLine();
        try (PreparedStatement psSearch = con.prepareStatement("SELECT * FROM Apartments WHERE id = ?")) {
            psSearch.setString(1, sSearch);
            try (ResultSet rsSearch = psSearch.executeQuery()) {
                int rowCounter = 0;
                ResultSetMetaData mdSearch = rsSearch.getMetaData();
                System.out.println();
                while (rsSearch.next()) {
                    rowCounter++;
                    System.out.print("Please enter column name which has to be amended (possible options: ");

                    Map<String, String> fields = new HashMap<String, String>();
                    for (int i = 2; i <= mdSearch.getColumnCount(); i++) {
                        if (i == mdSearch.getColumnCount()) {
                            fields.put(mdSearch.getColumnName(i), mdSearch.getColumnTypeName(i));
                            System.out.print(mdSearch.getColumnName(i) + "):");
                        } else {
                            fields.put(mdSearch.getColumnName(i), mdSearch.getColumnTypeName(i));
                            System.out.print(mdSearch.getColumnName(i) + ", ");
                        }
                    }
                    System.out.println();
                    String sInput = scan.nextLine().toLowerCase();
                    if (!fields.containsKey(sInput)) {
                        System.out.println("No parameter '" + sInput + "' in current table.\n");
                        return;
                    }
                    System.out.print("Please enter new data:");
                    String sNewData = scan.nextLine();

                    try (PreparedStatement psAmendment = con.prepareStatement("UPDATE Apartments SET " + sInput + " = ? WHERE id = ?")) {
                        psAmendment.setString(1, sNewData);
                        switch (fields.get(sInput)) {
                            case "INT" -> psAmendment.setInt(2, Integer.parseInt(sSearch));
                            case "DOUBLE" -> psAmendment.setDouble(2, Double.parseDouble(sSearch));
                            default -> psAmendment.setString(2, sSearch);
                        }

                        try {
                            psAmendment.executeUpdate();
                        } catch (SQLException e) {
                            System.out.println("Incorrect data is entered. Required type is " + fields.get(sInput) + "\n");
                            return;
                        }
                    }
                }
                if (rowCounter == 0) {
                    System.out.println("No ID " + sSearch + " has been found.\n");
                }
            }
        }
    }
}
