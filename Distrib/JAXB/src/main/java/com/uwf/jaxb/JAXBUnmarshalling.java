/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uwf.jaxb;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import recipe.Recipe;

public class JAXBUnmarshalling {
	public static void main(String[] args) throws ClassNotFoundException {
 
	 try {
 
		File file = new File("xmldata/recipe2.xml");
		JAXBContext jaxbContext = JAXBContext.newInstance(Class.forName("recipe.Recipe"));
 
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		Recipe r = (Recipe) jaxbUnmarshaller.unmarshal(file);
		System.out.println(r);
 
	  } catch (JAXBException e) {
		e.printStackTrace();
	  }
 
	}
}