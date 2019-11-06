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
public class JPAAdd {

    private final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAExamplePU");

     public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        Student roger = new Student();
        roger.setName("Roger Rabbit");
        roger.setAge(33);
        roger.setUWFId("4417777");
        roger.setMajor("Geocentric Studies");
        System.out.println(roger);
        
        em.getTransaction().begin();
        em.persist(roger);
        em.getTransaction().commit();
        
        System.out.println(roger);
    }
}
