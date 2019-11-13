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
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author Bernd
 */
@SuppressWarnings({"empty-statement"})
public class LibList {

    private static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("JPAExamplePU");
    private static final EntityManager EM = EMF.createEntityManager();
    private static Marshaller studentMarshaller;
    private static Marshaller bookMarshaller;

    static {
        try {
            studentMarshaller = JAXBContext.newInstance(Student.class).createMarshaller();
            bookMarshaller = JAXBContext.newInstance(Book.class).createMarshaller();

            // This can be a problem - here it isn't
            // Using static non-final variables in an initializer can be tricky,
            // since the sequence of statements can be random, just not in a static block
            // Warning has been disabled in the NB Configuration
            studentMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            bookMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        } catch (JAXBException e) {
        };
    }

    public static void allStudents() {
        System.out.println("\n******************************* Students **************************************\n");
        try {
            EM.clear();
            List<Student> sResults = EM.createNamedQuery("Student.findAll").getResultList();
            for (Student s : sResults) {
                studentMarshaller.marshal(s, System.out);
            }
        } catch (JAXBException e) {
        }
    }

    public static void allBooks() {
        System.out.println("\n********************************* Books ****************************************\n");
        try {
            EM.clear();
            List<Book> bResults = EM.createNamedQuery("Book.findAll").getResultList();
            for (Book b : bResults) {
                bookMarshaller.marshal(b, System.out);
            }
        } catch (JAXBException e) {
        }
    }

    public static void booksWithBorrowers() {
        System.out.println("\n************************* Books with borrowers ********************************\n");
        try {
            EM.clear();
            List<Book> bResults = EM.createQuery("SELECT b FROM Student s, Book b WHERE b.borrowerId = s").getResultList();
            for (Book b : bResults) {
                bookMarshaller.marshal(b, System.out);
            }
        } catch (JAXBException e) {
        };
    }

    public static void studentsWithBooks() {
        System.out.println("\n************************* Students with books *********************************\n");
        try {
            EM.clear();
            List<Object[]> borrowed = EM.createQuery("SELECT s,b FROM Student s, Book b WHERE b.borrowerId = s").getResultList();
            for (Object[] a : borrowed) {
                Student s = (Student) a[0];
                Book b = (Book) a[1];
                studentMarshaller.marshal(s, System.out);
                bookMarshaller.marshal(b, System.out);
            }
        } catch (JAXBException e) {
        };
    }
}
