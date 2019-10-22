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
public class Injection {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println(Password.validate("Ringo","s3cr3t"));
        System.out.println(PasswordSafe.validate("Ringo","s3cr3t"));
        System.out.println(Password.validate("Ringo","pass"));
        System.out.println(PasswordSafe.validate("Ringo","pass"));
        String inject = "\" or \"\"=\"";
        System.out.println(Password.validate(inject,inject));
        System.out.println(PasswordSafe.validate(inject,inject));
    }
    
}
