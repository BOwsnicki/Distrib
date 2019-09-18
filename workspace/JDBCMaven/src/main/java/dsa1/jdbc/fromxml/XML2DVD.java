/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dsa1.jdbc.fromxml;

import dsa1.jdbc.JDBCDescriptor;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;

/**
 *
 * @author Bernd
 */
public class XML2DVD {

    private static final String ENCODING = "UTF-8";
    private static final String xml = "<dvd id=\"4\"><title>Doctor Who</title><format>TV Series</format><genre>SciFi</genre></dvd>";

    public static void main(String[] args) throws Exception {
        Connection conn = new JDBCDescriptor("src/main/java/dsa1/jdbc/dbLocal.xml").getConnection();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(false);
        factory.setIgnoringElementContentWhitespace(true);
        DocumentBuilder builder = factory.newDocumentBuilder();

        // create the DOM from the string now
        Document doc = builder.parse(new ByteArrayInputStream(xml.getBytes(ENCODING)));

        // The rest works over XPath...
        XPathFactory xpfactory = XPathFactory.newInstance();
        XPath path = xpfactory.newXPath();

        // Check id - if exists update, else insert
        String id = path.evaluate("//@id", doc);
        String title = path.evaluate("//title", doc);
        String format = path.evaluate("//format", doc);
        String genre = path.evaluate("//genre", doc);

        String sql = "SELECT * FROM DVDS WHERE DVDId = \"" + id + "\"";

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        boolean found = rs.first();
        if (found) // Don't bother with finding out what has changed, just replace everything
        {
            sql = "UPDATE DVDS SET DVDTitle = \"" + title + "\", DVDFormat = \"" + format + "\", DVDGenre=\"" + genre + "\" WHERE DVDId = \"" + id + "\"";
        } else {
            sql = "INSERT INTO DVDS VALUES (" + id + ",\"" + title + "\",\"" + format + "\",\"" + genre + "\")";
        }

        int rowsAffected = stmt.executeUpdate(sql);
        assert (rowsAffected == 1);
    }
}
