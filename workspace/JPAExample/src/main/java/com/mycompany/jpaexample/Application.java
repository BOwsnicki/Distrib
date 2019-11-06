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

    private final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAExamplePU");
    private final static EntityManager em = emf.createEntityManager();

    private static void removeStudentByName(String name) {
        List<Student> named = em.createNamedQuery("Student.findByName").setParameter("name", name).getResultList();
        em.getTransaction().begin();
        for (Student s : named) {
            System.out.println(s);
            em.remove(s);
        }
        em.getTransaction().commit();
    }

    public static void main(String[] args) {
        removeStudentByName("Roger Rabbit");
        System.out.println("****************** Calling LibList ******************");
        LibList.main(args);
        System.out.println("****************** Calling JPAAdd ******************");
        JPAAdd.main(args);
        System.out.println("****************** Calling LibList ******************");
        LibList.main(args);
        System.out.println("****************** Calling LibList ******************");
        // LibUpdate.main(args);
        System.out.println("****************** Calling LibList ******************");
        // LibList.main(args);
    }
}
