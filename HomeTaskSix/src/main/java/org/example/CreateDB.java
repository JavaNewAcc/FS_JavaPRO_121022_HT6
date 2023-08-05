package org.example;

import com.mysql.cj.xdevapi.DatabaseObject;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDB {
    public static void initDB(Connection con) throws SQLException {
        try (Statement st = con.createStatement()) {
            st.execute("CREATE TABLE IF NOT EXISTS Apartments (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, district VARCHAR(20) NOT NULL, address VARCHAR(100) NOT NULL, area DOUBLE NOT NULL, rooms INT NOT NULL, price DOUBLE NOT NULL)");
        }
    }
}
