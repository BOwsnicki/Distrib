
import entities.DVDS;
import entities.Library;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author cop4856
 */
public class Model {

    static final String PERSISTENCE_UNIT_NAME = "JPAServletPU2";

    private static Marshaller JAXB_MARSHALLER = null;

    static {
        try {
            JAXB_MARSHALLER = JAXBContext.newInstance(Library.class).createMarshaller();
        } catch (JAXBException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public List<DVDS> getResultList() {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        try {
            emf = Persistence
                    .createEntityManagerFactory("JPAServletPU2");
            em = emf.createEntityManager();

            Query dvdByTitle = em.createNamedQuery("DVDS.findAll", DVDS.class);
            return dvdByTitle.getResultList();
        } finally {

        }
    }

    public Library getLibrary() {
        Library l = new Library();
        List<DVDS> list = getResultList();
        for (DVDS d : list) {
            l.add(d);
        }
        return l;
    }
    
    public String toXML() {
        try {
            StringWriter sw = new StringWriter();
            JAXB_MARSHALLER.marshal(getLibrary(), sw);
            return sw.toString();
        } catch (JAXBException ex) {
            return "";
        }
    }

    public String toJSON() {
        return "{ \"dvds\": "
                + getLibrary().toJSON()
                + "}";
    }
}
