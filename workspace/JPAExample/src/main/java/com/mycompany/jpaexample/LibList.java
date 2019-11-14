/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jpaexample;

import java.util.List;
import javax.persistence.EntityManager;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author Bernd
 */
@SuppressWarnings({"empty-statement"})
public class LibList {

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

    public static void allStudents(EntityManager em) {
        System.out.println("\n******************************* Students **************************************\n");
        try {
            List<Student> sResults = em.createNamedQuery("Student.findAll").getResultList();
            for (Student s : sResults) {
                studentMarshaller.marshal(s, System.out);
            }
        } catch (JAXBException e) {
        }
    }

    public static void allBooks(EntityManager em) {
        System.out.println("\n********************************* Books ****************************************\n");
        try {
            List<Book> bResults = em.createNamedQuery("Book.findAll").getResultList();
            for (Book b : bResults) {
                bookMarshaller.marshal(b, System.out);
            }
        } catch (JAXBException e) {
        }
    }

    public static void booksWithBorrowers(EntityManager em) {
        System.out.println("\n************************* Books with borrowers ********************************\n");
        try {
            List<Book> bResults = em.createQuery("SELECT b FROM Student s, Book b WHERE b.borrowerId = s").getResultList();
            for (Book b : bResults) {
                bookMarshaller.marshal(b, System.out);
            }
        } catch (JAXBException e) {
        };
    }

    public static void studentsWithBooks(EntityManager em) {
        System.out.println("\n************************* Students with books *********************************\n");
        try {
            List<Object[]> borrowed = em.createQuery("SELECT s,b FROM Student s, Book b WHERE b.borrowerId = s").getResultList();
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
