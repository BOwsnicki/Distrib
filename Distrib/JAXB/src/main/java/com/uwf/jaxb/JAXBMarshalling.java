/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uwf.jaxb;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import recipe.Ingredient;
import recipe.Instructions;
import recipe.Recipe;

/**
 *
 * @author bernd
 */
public class JAXBMarshalling {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Recipe r = new Recipe();
        r.setTitle("Chicken soup");

        Instructions instr = new Instructions();
        r.setInstructions(instr);
        List<String> step = instr.getStep();
        step.add("Heat water");
        step.add("Add chicken");
        step.add("Boil for 5 hours");

        List<Ingredient> ingr = r.getIngredient();
        ingr.add(new Ingredient("Water", "3", "gallon"));
        ingr.add(new Ingredient("Chicken", "1", "piece", "dead"));

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Recipe.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(r, System.out);
        } catch (Exception ex) {
            Logger.getLogger(JAXBMarshalling.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
