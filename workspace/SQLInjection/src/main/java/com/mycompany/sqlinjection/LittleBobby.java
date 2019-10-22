/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sqlinjection;

/**
 *
 * @author bowsnickiklewe
 */
public class LittleBobby {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String user = "Robert\"; DROP TABLE Users; -- ";
        String pass = "pass";
        
        // Doesn't really work, since MySQL doesn't allow
        // to pass multiple commands in one session anymore (smart!)
        System.out.println(Password.validate(user,pass));
    }
    
}
