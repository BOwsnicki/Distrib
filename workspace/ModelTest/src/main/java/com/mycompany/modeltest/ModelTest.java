/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modeltest;

import com.mycompany.entities.DVD;
import com.mycompany.entities.DVDDAO;
import com.mycompany.model.Model;
import java.util.List;

/**
 *
 * @author cop4856
 */
public class ModelTest {
    public static void main(String[] args) {
        
        List<DVD> result = new DVDDAO().findAll();
        
        for (DVD d : result) {
            System.out.println(d);
        }
        
        Model m = new Model();
        System.out.println(m.toXML());
        
    }
}
