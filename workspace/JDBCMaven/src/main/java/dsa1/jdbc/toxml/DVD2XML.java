/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dsa1.jdbc.toxml;

import dsa1.jdbc.JDBCDescriptor;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 *
 * @author Bernd
 */
public class DVD2XML {

    public static void main(String[] args) {
        try {
            Connection conn = new JDBCDescriptor("src/main/java/dsa1/jdbc/dbLocal.xml").getConnection();
            // BEGIN TRANSACTION
            conn.setAutoCommit(false);

            // Get all DVDs
            String sql = "SELECT * from DVDS";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // Here goes the XML
            // More or less like in the example in the XML chapter - just different :-)
            DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            //create the root element and add it to the document
            Element root = doc.createElement("library");
            doc.appendChild(root);

            Element child;
            while (rs.next()) {
                child = doc.createElement("dvd");
                root.appendChild(child);
                child.setAttribute("id", rs.getString(1));

                Element grandChild = doc.createElement("title");
                grandChild.appendChild(doc.createTextNode(rs.getString(2)));
                child.appendChild(grandChild);

                grandChild = doc.createElement("format");
                grandChild.appendChild(doc.createTextNode(rs.getString(3)));
                child.appendChild(grandChild);

                grandChild = doc.createElement("genre");
                grandChild.appendChild(doc.createTextNode(rs.getString(4)));
                child.appendChild(grandChild);           
        }
 
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(new DOMSource(doc), new StreamResult(System.out));
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState:     " + e.getSQLState());
            System.out.println("VendorError:  " + e.getErrorCode());
        } catch (ClassNotFoundException e) {
            System.out.println("Cannot load JDBC driver");
        } catch (ParserConfigurationException e) {
            System.out.println("DB descriptor file could not be read");
        } catch (SAXException e) {
            System.out.println("DB descriptor file could not be read");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
