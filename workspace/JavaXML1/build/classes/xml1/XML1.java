/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xml1;

import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import java.io.*;

public class XML1 {

    public static void main(String[] args)
            throws TransformerException,
            TransformerConfigurationException,
            FileNotFoundException, IOException {
        Transformer transformer = TransformerFactory.newInstance().newTransformer(new StreamSource("src/data/stocks.xsl"));
        System.out.println(transformer.getClass());
        transformer.transform(new StreamSource("src/data/stocks.xml"), new StreamResult(System.out));
    }
}