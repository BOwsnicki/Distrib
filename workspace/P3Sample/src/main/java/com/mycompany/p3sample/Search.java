package com.mycompany.p3sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author Bernd OK
 */
public class Search {

    private static String readWithPrompt(Scanner s) {
        System.out.print("Enter keyword (QUIT to end)> ");
        return s.nextLine();
    }

    public static void main(String[] args) {
        final String driverName = "com.mysql.cj.jdbc.Driver";
        final String dbURL = "jdbc:mysql://localhost:3306/P3Sample?serverTimezone=UTC&user=root&password=mysql4me";
        final String SQL = "SELECT * FROM Playlist WHERE Key1 = ? OR Key2 = ? OR Key3 = ? OR Key4 = ? OR Key5 = ?";

        Connection conn = null;
        PreparedStatement stmt = null;

        try { // set everything up, exit if there's something wrong
            Class.forName(driverName);
            conn = DriverManager.getConnection(dbURL);
            stmt = conn.prepareStatement(SQL);
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState:     " + e.getSQLState());
            System.out.println("VendorError:  " + e.getErrorCode());
            System.exit(0);
        } catch (ClassNotFoundException e) {
            System.out.println("Cannot load JDBC driver");
            System.exit(0);
        }

        try {
            Scanner inp = new Scanner(System.in);
            String input = readWithPrompt(inp);

            while (!("QUIT".equalsIgnoreCase(input))) {
                stmt.setString(1, input);
                stmt.setString(2, input);
                stmt.setString(3, input);
                stmt.setString(4, input);
                stmt.setString(5, input);

                ResultSet rs = stmt.executeQuery();
                boolean gotOne = false; // No good way to check if a ResultSet is empty but to try it!
                while (rs.next()) {
                    gotOne = true;
                    System.out.println("IMDBRef:\t\t" + rs.getString(1));
                    System.out.println("Title:\t\t\t" + rs.getString(2));
                    System.out.println("Metascore:\t\t" + rs.getString(3));
                    System.out.println("Totten Tomatoes:\t" + rs.getString(4));
                    System.out.print("Keywords:\t\t");
                    for (int i = 5; i < 10; i++) {
                        System.out.print(rs.getString(i) + " ");
                    }
                    System.out.println();
                    System.out.println();
                }
                if (!gotOne) {
                    System.out.println("\nNo movie found with this keyword!\n");
                }
                input = readWithPrompt(inp);
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState:     " + e.getSQLState());
            System.out.println("VendorError:  " + e.getErrorCode());
            System.exit(0);
        } catch (Exception e) { // shouldn't happen!
            e.printStackTrace(System.err);
        } finally {
            try {
                conn.close();
                stmt.close();
            } catch (SQLException e) {
            }
        }
    }
}
