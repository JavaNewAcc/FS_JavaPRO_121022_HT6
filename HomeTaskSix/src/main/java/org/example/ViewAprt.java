package org.example;

import java.sql.*;

public class ViewAprt {
    public static void viewAprt(Connection con) throws SQLException {
        try (PreparedStatement ps = con.prepareStatement("SELECT * FROM  Apartments")) {
            try (ResultSet rs = ps.executeQuery()) {
                ResultSetMetaData md = rs.getMetaData();
                for (int i = 1; i <= md.getColumnCount(); i++) {
                    System.out.print(md.getColumnName(i) + "\t\t\t");
                }
                System.out.println();
                while (rs.next()) {
                    for (int i = 1; i <= md.getColumnCount(); i++) {
                        System.out.print(rs.getString(i) + "\t\t\t");
                    }
                    System.out.println();
                }
                System.out.println();
            }
        }
    }
}
