/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cop4856
 */
public class DVDDO {
    // A very simple Data Object abstraction for DB access via JDBC
    private List<DVD> resultList;

    public DVDDO() {
        Statement stmt = null;
        Connection conn = null;
        ResultSet rs = null;
        final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
        final String DB_URL = "jdbc:mysql://localhost:3306/DVDLibrary?serverTimezone=UTC&user=root&password=mysql4me";

        try {
            resultList = new ArrayList<>();
            Class.forName(DRIVER_NAME);
            conn = DriverManager.getConnection(DB_URL);
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * from DVDS");
            while (rs.next()) {
                DVD d = new DVD(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                resultList.add(d);
            }
        } catch (ClassNotFoundException | SQLException e) {
            Logger.getLogger(DVDDO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try { // Did I mention I hate "try" in a "finally" clause...
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                Logger.getLogger(DVDDO.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    public List<DVD> getResultList() {
        return resultList;
    }
}
