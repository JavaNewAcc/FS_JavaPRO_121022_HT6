package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class AddAprt {
    public static void addAprt(Scanner scan, Connection con) throws SQLException {
        System.out.println("In order to add new apartment we shall need some information (all fields are mandatory):");
        System.out.println("Enter district: ");
        String district = scan.nextLine();
        System.out.println("Enter address: ");
        String address = scan.nextLine();
        System.out.println("Enter apartment area: ");
        String sArea = scan.nextLine();
        double area = Double.parseDouble(sArea);
        System.out.println("Enter number of rooms: ");
        String sRooms = scan.nextLine();
        int rooms = Integer.parseInt(sRooms);
        System.out.println("Enter price: ");
        String sPrice = scan.nextLine();
        double price = Double.parseDouble(sPrice);
        System.out.println();

        try (PreparedStatement ps = con.prepareStatement("INSERT INTO Apartments (district, address, area, rooms, price) VALUES (?, ?, ?, ?, ?)")) {
            ps.setString(1, district);
            ps.setString(2, address);
            ps.setDouble(3, area);
            ps.setInt(4, rooms);
            ps.setDouble(5, price);
            ps.executeUpdate();
        }
    }
}
