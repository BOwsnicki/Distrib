/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jpaexample;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

/**
 *
 * @author Bernd
 */
public class LibList {

    private final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAExamplePU");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        /*
        List<Student> sResults = em.createNamedQuery("Student.findAll").getResultList();
        for (Student s : sResults) {
            System.out.println(s);
        }
        */
        try {
            JAXBContext jaxbContext
                    = JAXBContext.newInstance(Student.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // Make the output pretty printed:
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            List<Student> sResults = em.createNamedQuery("Student.findAll").getResultList();
            for (Student s : sResults) {
                jaxbMarshaller.marshal(s, System.out);
            }
         
            jaxbContext = JAXBContext.newInstance(Book.class);
            jaxbMarshaller = jaxbContext.createMarshaller();  
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            List<Book> bResults = em.createNamedQuery("Book.findAll").getResultList();
            for (Book b : bResults) {
                jaxbMarshaller.marshal(b, System.out);
            }
        } catch (Exception ex) {
        }

        /*
        List<Book> bResults = em.createNamedQuery("Book.findAll").getResultList();
        for (Book b : bResults) {
            System.out.println(b);
        }
        

        bResults = em.createNamedQuery("Book.findByTitle").setParameter("title", "The Civil War").getResultList();
        for (Book b : bResults) {
            System.out.println(b);
        }

        List<Object[]> borrowed = em.createQuery("SELECT s,b FROM Student s, Book b WHERE b.borrowerId = s").getResultList();
        for (Object[] a : borrowed) {
            Student s = (Student) a[0];
            Book b = (Book) a[1];
            System.out.println("" + b + " " + s);
        }

        bResults = em.createQuery("SELECT b FROM Student s, Book b WHERE b.borrowerId = s").getResultList();
        for (Book b : bResults) {
            System.out.println(b);
            System.out.println(b.getBorrowerId());
        }
        */
    }
}
