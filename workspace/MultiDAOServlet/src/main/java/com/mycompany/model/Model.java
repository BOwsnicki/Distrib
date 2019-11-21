/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.model;

import com.mycompany.entities.DVDDAO;
import com.mycompany.entities.DVD;
import java.io.StringWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author cop4856
 */
public class Model {
    private static Marshaller JAXB_MARSHALLER = null;

    static {
        try {
            JAXB_MARSHALLER = JAXBContext.newInstance(Library.class).createMarshaller();
        } catch (JAXBException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private final Library library;

    public Model() {
        library = new Library();

        for (DVD d : new DVDDAO().findAll()) {
            library.add(d);
        }
    }

    public String toXML() {
        try {
            StringWriter sw = new StringWriter();
            JAXB_MARSHALLER.marshal(library, sw);
            return sw.toString();
        } catch (JAXBException ex) {
            return "";
        }
    }

    public String toJSON() {
        return "{ \"dvds\": "
                + library.toJSON()
                + "}";
    }

}
