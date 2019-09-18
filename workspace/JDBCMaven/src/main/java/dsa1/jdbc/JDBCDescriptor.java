package dsa1.jdbc;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author Bernd
 */
public class JDBCDescriptor {

    private String driver;
    private String protocol;
    private String host;
    private int port;
    private String schema;
    private String user;
    private String password;
    private DocumentBuilder builder;
    private XPath path;

    public JDBCDescriptor(String fileName, boolean validating)
            throws ParserConfigurationException,
            SAXException,
            IOException,
            XPathExpressionException {
        // Initialize DOM builder and XPath tool
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(validating);
        factory.setIgnoringElementContentWhitespace(true);
        builder = factory.newDocumentBuilder();
        XPathFactory xpfactory = XPathFactory.newInstance();
        path = xpfactory.newXPath();

        // open the input file and create the DOM
        File f = new File(fileName);
        Document doc = builder.parse(f);

        // extract the relevant fields
        driver = path.evaluate("//driver", doc);
        protocol = path.evaluate("//protocol", doc);
        host = path.evaluate("//host", doc);
        schema = path.evaluate("//schema", doc);
        port = Integer.parseInt(path.evaluate("//port", doc));
        user = path.evaluate("//user", doc);
        password = path.evaluate("//password", doc);
    }

    public JDBCDescriptor(String file)
            throws ParserConfigurationException,
            SAXException,
            IOException,
            XPathExpressionException {
        this(file, false);
    }

    // Ignore this on first reading! It might become interesting later - much later!
    public static Context asContext(String fileName)
            throws ParserConfigurationException,
            SAXException,
            IOException,
            XPathExpressionException,
            NamingException {
        // Initialize DOM builder and XPath tool
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(false);
        factory.setIgnoringElementContentWhitespace(true);
        DocumentBuilder b = factory.newDocumentBuilder();
        XPathFactory xpfactory = XPathFactory.newInstance();
        XPath p = xpfactory.newXPath();

        // open the input file and create the DOM
        File f = new File(fileName);
        Document doc = b.parse(f);

        Context ctx = new InitialContext();
        ctx.bind("driver", p.evaluate("//driver", doc));
        ctx.bind("protocol", p.evaluate("//protocol", doc));
        ctx.bind("host", p.evaluate("//host", doc));
        ctx.bind("schema", p.evaluate("//schema", doc));
        ctx.bind("port", Integer.parseInt(p.evaluate("//port", doc)));
        ctx.bind("user", p.evaluate("//user", doc));
        ctx.bind("password", p.evaluate("//password", doc));
        return ctx;
    }

    @Override
    public String toString() {
        return getPath();
    }

    public String getPath() {
        return protocol + "://" + host + ":" + port + "/" + schema + "?serverTimezone=UTC" + "&user=" + user + "&password=" + password;
    }

    public String getDriver() {
        return driver;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getSchema() {
        return schema;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public Connection getConnection()
            throws ClassNotFoundException, SQLException {
        Class.forName(getDriver());
        return DriverManager.getConnection(getPath());
    }
}
