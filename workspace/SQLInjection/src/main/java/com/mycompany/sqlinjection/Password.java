/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sqlinjection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author bowsnickiklewe
 */
public class Password {

    public static boolean validate(String user, String password) {
        final String driverName = "com.mysql.cj.jdbc.Driver";
        final String dbURL = "jdbc:mysql://localhost:3306/Users?serverTimezone=UTC&user=root&password=mysql4me";
        try {
            Class.forName(driverName);
            Connection conn = DriverManager.getConnection(dbURL);
            Statement stmt = conn.createStatement();
            String sql = "SELECT * from Users WHERE Uname = \"" + user + "\" AND Passwd = \"" + password + "\"";
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                System.out.print(rs.getString(1));
                System.out.print(" " + rs.getString(2));
                System.out.println(" " + rs.getString(3));
                return true;
            }
        } catch (ClassNotFoundException ex) {
            return false;
        } catch (SQLException ex) {
            return false;
        }
        return false;
    }
}
