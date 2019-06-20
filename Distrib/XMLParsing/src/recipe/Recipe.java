/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package recipe;

/**
 *
 * @author Bernd
 */
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

 /*
 * @author Bernd
 */
public class Recipe {
    private Attributes savedAttributes;
    private String elemString;
    
    private String title;
    private ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
    private ArrayList<String> steps = new ArrayList<String>();
    
    // Just the usual interface - nothing special
    public String getTitle() { return title; }
    public List<Ingredient> getIngredients() { return ingredients; }
    public List<String> getSteps() { return steps; }
    
    // ============================== SAX parsing ===========================
    
    public static Recipe fromSAX(String fileName) { 
        Recipe r = new Recipe();
        r.parseDocument(fileName);
        return r; 
    }
    
    class RecipeHandler extends DefaultHandler
    {
        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            	elemString = new String(ch,start,length);
	}
        @Override
        public void startElement(String uri, String localName, String qName,
		Attributes attributes) throws SAXException {
                savedAttributes = attributes;
        }
    
        @Override
        public void endElement(String uri, String localName, String qName) 
            throws SAXException {
                if (qName.equals("title")) title = elemString;             
                if (qName.equals("ingredient")) {
                    float amount = Float.parseFloat(savedAttributes.getValue("amount"));
                    String unit  = savedAttributes.getValue("unit");
                    String state = savedAttributes.getValue("state");
                    ingredients.add((state == null 
                                        ? new Ingredient(elemString,amount,unit) 
                                        : new Ingredient(elemString,amount,unit,state)));
                }
                if (qName.equals("step")) steps.add(elemString);
        }
    }
    private void parseDocument(String fileName) {
        //get a factory
	SAXParserFactory spf = SAXParserFactory.newInstance();
	try {   // get a new instance of parser
		SAXParser sp = spf.newSAXParser();
                System.out.println(sp.getClass());
		//parse the file and also register a new handler for callbacks
		sp.parse(fileName, new RecipeHandler());
	} catch (SAXException se) { se.printStackTrace();
	} catch (ParserConfigurationException pce) { pce.printStackTrace();
	} catch (IOException ie) { ie.printStackTrace();
	}
    }
    
   
    // ============================== DOM parsing ===========================
    
    public static Recipe fromDOM(String fileName) { 
        Recipe r = new Recipe();
        try {
            r.buildDOM(fileName);
        } catch (Exception e) { e.printStackTrace(); return null; }
        return r; 
    }
    
    private void buildDOM(String fileName) 
            throws  SAXException, IOException, 
                    ParserConfigurationException, XPathExpressionException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(false);
        factory.setIgnoringElementContentWhitespace(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        
        // open the input file and create the DOM
        File f = new File(fileName);
        Document doc = builder.parse(f);
        
        XPathFactory xpfactory = XPathFactory.newInstance();
        XPath path = xpfactory.newXPath();
        
        title = path.evaluate("//title", doc);
        int numIngredients = Integer.parseInt(path.evaluate("count(//ingredient)",doc));
        // Watch it! They start counting at 1!
        for (int i = 1; i <= numIngredients; i++) {
            String name = path.evaluate("//ingredient[" + i + "]",doc);
            float amount = Float.parseFloat(path.evaluate("//ingredient[" + i + "]/@amount",doc));
            String unit = path.evaluate("//ingredient[" + i + "]/@unit",doc);
            String state = path.evaluate("//ingredient[" + i + "]/@state",doc);
            ingredients.add((state.equals("")
                                ? new Ingredient(name,amount,unit) 
                                : new Ingredient(name,amount,unit,state)));
        }
        
        int numSteps = Integer.parseInt(path.evaluate("count(//step)",doc));
        for (int i = 1; i <= numSteps; i++)
            steps.add(path.evaluate("//step[" + i + "]",doc));
    }
   
    @Override
    public String toString() {
        String result = "Recipe for " + title + ":\n";
        for (Ingredient i : ingredients) result += i + "\n";
        for (String s : steps) result += s + "\n";
        return result;
    }
    
    public static void main(String[] args)
    {
        Recipe r;
        r = Recipe.fromSAX("src/recipe.xml");
        System.out.println(r);
        r = Recipe.fromDOM("src/recipe.xml");
        System.out.println(r);
    }
}
