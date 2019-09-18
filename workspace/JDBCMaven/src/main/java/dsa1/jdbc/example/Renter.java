package dsa1.jdbc.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Bernd OK
 */
public class Renter {

    private static void borrowBook(Connection conn, String studentName, String bkTitle)
            throws SQLException, StudentNotFoundException, BookNotFoundException,
                   StudentNotUniqueException, BookNotUniqueException,
                   BookNotAvailableException {
        Statement stmt = conn.createStatement();

        // BEGIN TRANSACTION
        conn.setAutoCommit(false);

        // Find student by name, grab the primary key
        String sql = "SELECT StudentId from Student WHERE Name = \"" + studentName + "\"";
        // System.out.println(sql);
        ResultSet rs = stmt.executeQuery(sql);

        // Case 1: No rows - no student!
        if (!rs.next()) {
            conn.rollback();
            conn.setAutoCommit(true);
            throw new StudentNotFoundException("Student name " + studentName + " not found");
        }
        // Case 2: More rows - name not unique!
        if (!rs.isLast()) {
            conn.rollback();
            conn.setAutoCommit(true);
            throw new StudentNotUniqueException("Student name " + studentName + " not unique");
        }
        // Save studentID (primary key)
        String sID = rs.getString(1);

        // Find book by name, look at bookID (primary key) and borrowerID (foreign key)
        sql = "SELECT BookID, BorrowerID FROM Book WHERE Title = \"" + bkTitle + "\"";
        // System.out.println(sql);
        rs = stmt.executeQuery(sql);

        // Case 3: No such book
        if (!rs.next()) {
            conn.rollback();
            conn.setAutoCommit(true);
            throw new BookNotFoundException("Book title " + bkTitle + " not found");
        }
        // Case 4: More than one book with this title
        if (!rs.isLast()) {
            conn.rollback();
            conn.setAutoCommit(true);
            throw new BookNotUniqueException("Book title " + bkTitle + " not unique");
        }
        // Case 5: Foreign key not null - not avilable!
        if (rs.getString(2) != null) {
            conn.rollback();
            conn.setAutoCommit(true);
            throw new BookNotAvailableException("Book title " + bkTitle + " not available");
        }

        // Finally! Get this book
        // Imagine this whole thing without a transaction!!
        sql = "UPDATE Book" +
                    " SET BorrowerId = " + sID +
                    " WHERE BookID = " + rs.getString(1);
        int rowsAffected = stmt.executeUpdate(sql);

        // Ok! That's a tricky one...
        // Technically rowsAffected *must be* 1, since BookID is the primary key
        // and we know it's there - we just found it.
        // And with everything that must be the case, it's always nice
        // to be notified if it isn't. You never know...
        // The weapon of choice for situations like this: assert!
        assert(rowsAffected == 1);

        // Ok, finally!
        conn.commit();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Statement stmt = null;
        ResultSet rs = null;
        Connection conn = null;
        final String driverName = "com.mysql.cj.jdbc.Driver";
        final String dbURL = "jdbc:mysql://localhost:3306/Library?serverTimezone=UTC&user=root&password=mysql4me";
        //final String dbURL = "jdbc:mysql://mysql.cs.uwf.edu/bowsnickiklewe?user=bowsnickiklewe&amp;password=KApuCR2s";

        // OK! Here we go. All in a big try - we'll check the exceptions later
        try {
            Class.forName(driverName);
            conn = DriverManager.getConnection(dbURL);
            stmt = conn.createStatement();

            // Later we might make this more flexible by
            // reading it from the keyboard or a Swing box...
            String sName = "Jane Doe";
            String bTitle = "Anna Karenina";
            borrowBook(conn,sName,bTitle);

            // If we got that far... the rest is just a piece of cake (I guess)
            // 1. List all students
            rs = stmt.executeQuery("SELECT * from Student");
            while (rs.next()) {
                System.out.print(rs.getString(1));
                System.out.print(" " + rs.getString(2));
                System.out.print(" " + rs.getString(3));
                System.out.print(" " + rs.getString(4));
                System.out.println(" " + rs.getString(5));
            }
            // 2. List all books
            rs = stmt.executeQuery("SELECT * from Book");
            while (rs.next()) {
                System.out.print(rs.getString(1));
                System.out.print(" " + rs.getString(2));
                System.out.print(" " + rs.getString(3));
                System.out.print(" " + rs.getString(4));
                System.out.println(" " + rs.getString(5));
            }
            //3. I have no idea what that does...
            rs = stmt.executeQuery("SELECT CatalogNumber, Title, UwfID, Name FROM Book, Student WHERE Book.BorrowerID = Student.StudentID");
            while (rs.next()) {
                System.out.print(rs.getString(1));
                System.out.print(" " + rs.getString(2));
                System.out.print(" " + rs.getString(3));
                System.out.println(" " + rs.getString(4));
            }
        // And now comes the fun part: Exception handling
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState:     " + e.getSQLState());
            System.out.println("VendorError:  " + e.getErrorCode());
        } catch (ClassNotFoundException e) {
            System.out.println("Cannot load JDBC driver");
        } catch (LibraryException e) { // Hey! That's one of ours...
            System.out.println(e.getMessage());
        } catch (Exception e) { // This is really the worst case...
            e.printStackTrace();
        } finally {
            try { // Did I mention I hate "try" in a "finally" clause...
                rs.close();
                stmt.close();
                conn.close();
            } catch (Exception e) {
                // Out of ideas! I give up...
            }
        }
    }
}
