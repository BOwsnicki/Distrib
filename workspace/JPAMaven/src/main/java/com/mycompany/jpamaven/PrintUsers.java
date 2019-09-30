/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jpamaven;

// import com.fasterxml.jackson.core.JsonProcessingException;
// import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author bowsnickiklewe
 */
public class PrintUsers {

    private static final String PERSISTENCE_UNIT_NAME = "JPAPU";

    public static void main(String[] args) throws JAXBException { //), JsonProcessingException {
        {
            // 1. JPA stuff
            EntityManagerFactory emf = Persistence
                    .createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
            EntityManager em = emf.createEntityManager();

            // 2. Query DB
            Query allUsers = em.createNamedQuery("Users.findAll", Users.class);
            List<Users> result = allUsers.getResultList();
            for (Users u : result) {
                System.out.println(u);
            }

            // 3. JAXB Setup
            JAXBContext pContext = JAXBContext.newInstance(Users.class);
            Marshaller marshaller = pContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            /*
        // 4. Jackson Setup
        ObjectMapper objectMapper = new ObjectMapper();
             */
            // 5. Throw it out...
            for (Users u : result) {
                System.out.println(u);
                marshaller.marshal(u, System.out);
//            System.out.println(objectMapper.writeValueAsString(u));
            }
        }
    }
}
