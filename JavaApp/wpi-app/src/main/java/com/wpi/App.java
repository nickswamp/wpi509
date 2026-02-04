package com.wpi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class App {
    public static void main(String[] args) {
        String url = "jdbc:mysql://db:3306/semester_project";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, "wpi_user", "wpi_password");

            System.out.println("      JAVA DATABASE CONNECTION SUCCESS    ");

            // Run First Query
            System.out.println("\n--- Query 1: Deltas (Limit 1) ---");
            printQuery(conn, "select * from deltas limit 1");

            // Run Second Query
            System.out.println("\n--- Query 2: Southwests (Limit 1) ---");
            printQuery(conn, "select * from southwests limit 1");

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printQuery(Connection conn, String sql) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        // This gets information about the columns
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        while (rs.next()) {
            // Loop through all columns in the row and print
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(metaData.getColumnName(i) + ": " + rs.getString(i));
                if (i < columnCount)
                    System.out.print(" | ");
            }
            System.out.println();
        }
    }
}