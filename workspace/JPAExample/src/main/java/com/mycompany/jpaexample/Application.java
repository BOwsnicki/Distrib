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

/**
 *
 * @author cop4856
 */
public class Application {

    private final static EntityManagerFactory EMF = Persistence.createEntityManagerFactory("JPAExamplePU");
    private final static EntityManager EM = EMF.createEntityManager();

    private static void releaseBook(int id) {
        Book b = EM.find(Book.class, id);
        b.setBorrowerId(null);
        EM.getTransaction().begin();
        EM.persist(b);
        EM.getTransaction().commit();
    }

    private static void removeStudentByName(String name) {
        List<Student> named = EM.createNamedQuery("Student.findByName").setParameter("name", name).getResultList();
        EM.getTransaction().begin();
        for (Student s : named) {
            EM.remove(s);
        }
        EM.getTransaction().commit();
    }

    private static void addRoger() {
        Student roger = new Student();
        roger.setName("Roger Rabbit");
        roger.setAge(33);
        roger.setUWFId("4417777");
        roger.setMajor("Geocentric Studies");
        System.out.println(roger);

        EM.getTransaction().begin();
        EM.persist(roger);
        EM.getTransaction().commit();

        System.out.println(roger);
    }

    private static void borrowHarry() {
        // Lisa Simpson (id 5) borrows  Harry Potter and the Deathly Hallows (4)
        Student lisa = EM.find(Student.class, 5);
        Book harry = EM.find(Book.class, 4);

        // Now...
        harry.setBorrowerId(lisa);    // nice!
        EM.getTransaction().begin();
        EM.persist(harry);
        EM.getTransaction().commit();

        System.out.println(harry);
    }

    public static void main(String[] args) {
        removeStudentByName("Roger Rabbit");
        releaseBook(4);
        System.out.println("****************** Calling LibList ******************");
        LibList.allStudents(EM);
        LibList.allBooks(EM);
        LibList.booksWithBorrowers(EM);
        System.out.println("******************** Calling Add ********************");
        addRoger();
        System.out.println("****************** Calling LibList ******************");
        LibList.allStudents(EM);
        System.out.println("******************* Calling Update ******************");
        borrowHarry();
        System.out.println("****************** Calling LibList ******************");
        LibList.booksWithBorrowers(EM);
        LibList.studentsWithBooks(EM);
        System.out.println("****************** Calling Remove *******************");
        try {
            removeStudentByName("Jane Doe");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
