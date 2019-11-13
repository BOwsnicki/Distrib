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
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Bernd
 */
public class LibUpdate {

    private final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAExamplePU");

    public static Student findStudent(EntityManager em, int id) {
        return em.find(Student.class, id);
    }

    public static Book findBook(EntityManager em, int id) {
        return em.find(Book.class, id);
    }

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        // Lisa Simpson (id 5) borrows  Harry Potter and the Deathly Hallows (4)
        Student lisa = findStudent(em, 5);
        Book harry = findBook(em, 4);

        // Now...
        harry.setBorrowerId(lisa);    // nice!
        em.getTransaction().begin();
        em.persist(harry);
        em.getTransaction().commit();

        System.out.println(harry);
    }
}
