/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import static com.sun.org.apache.xerces.internal.impl.Constants.JDK_ENTITY_EXPANSION_LIMIT;
import java.io.File;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Bernd
 */
public class XMLTraverser {

    private static DocumentBuilder builder;
    private static XPath path;
    private static final String[] NODE_TYPE_NAMES = {"", "ELEMENT", "ATTRIBUTE", "TEXT"};

    public static void traverse(String fileName)
            throws IOException, XPathExpressionException {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setValidating(false);
            factory.setIgnoringElementContentWhitespace(true);
            // factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            // factory.setAttribute(JDK_ENTITY_EXPANSION_LIMIT, 1000);
            builder = factory.newDocumentBuilder();
            XPathFactory xpfactory = XPathFactory.newInstance();
            path = xpfactory.newXPath();

            // open the input file and create the DOM
            File f = new File(fileName);
            Document doc = builder.parse(f);

            traverseInternal(doc.getDocumentElement(), "");
        } catch (SAXException e) {
            System.err.println("Parse aborted!");
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XMLTraverser.class.getName()).log(Level.SEVERE, null, ex);
        };
    }

    private static void traverseInternal(Node n, String indent) {
        System.out.println(indent + "Node Name: " + n.getNodeName());
        System.out.println(indent + "Node Type: " + NODE_TYPE_NAMES[n.getNodeType()]);
        System.out.println(indent + "Node Value: *" + n.getNodeValue() + "*");

        NamedNodeMap attrs = n.getAttributes();
        if (false) {
            System.out.println(indent + "-------------------- attributes -------------");
            for (int i = 0; i < attrs.getLength(); i++) {
                // Attr a = (Attr)attrs.item(i);
                traverseInternal(attrs.item(i), indent + "  ");
                // System.out.println(indent + a.getName());
            }
            System.out.println(indent + "-------------------- setubirtta -------------");
        } else {
            System.out.println(indent + "Attributes null!");
        }

        NodeList children = n.getChildNodes();
        System.out.println(indent + children.getLength() + " children:");
        for (int i = 0; i < children.getLength(); i++) {
            traverseInternal(children.item(i), indent + "  ");
        }
    }

    public static void main(String[] args) throws Exception {
        XMLTraverser.traverse("src/library2.xml");
    }
}
