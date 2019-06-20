/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xml3;

import org.w3c.dom.*;

import javax.xml.parsers.*;

import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

/**
 *
 * @author Bernd
 */
public class XML3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String[] ingredients = {"Flour", "Yeast", "Water", "Salt"};
        String[] amounts = {"3", "0.25", "1.5", "1"};
        String[] units = {"cups", "ounce", "cups", "teaspoon"};
        String[] states = {null, null, "warm", null};

        String[] steps = {
            "Mix all ingredients together.",
            "Knead thoroughly.",
            "Cover with a cloth, and leave for one hour in warm room.",
            "Knead again.",
            "Place in a bread baking tin.",
            "Cover with a cloth, and leave for one hour in warm room.",
            "Bake in the oven at 350(degrees)F for 30 minutes."
        };


        try {

            //We need a Document
            DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            //create the root element and add it to the document
            Element root = doc.createElement("recipe");
            doc.appendChild(root);

            // Put in the title
            Element child = doc.createElement("title");
            child.appendChild(doc.createTextNode("Basic bread"));
            root.appendChild(child);

            // Dysmal coding - put in the ingredients
            // use a "normal" for loop - we access four arrays in parallel
            for (int i = 0; i < ingredients.length; i++) {
                child = doc.createElement("ingredient");
                child.setAttribute("amount", amounts[i]);
                child.setAttribute("unit", units[i]);
                if (states[i] != null) child.setAttribute("state", states[i]);
                child.appendChild(doc.createTextNode(ingredients[i]));
                root.appendChild(child);

            }
            for (String step : steps) {
                child = doc.createElement("step");
                child.appendChild(doc.createTextNode(step));
                root.appendChild(child);
            }

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            // You might want to set that to "no" later...
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(new DOMSource(doc), new StreamResult(System.out));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
