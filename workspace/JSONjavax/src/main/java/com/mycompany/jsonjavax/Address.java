/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jsonjavax;

/**
 *
 * @author bowsnickiklewe
 */
public class Address {
    private String street;
    private String city;
    private int zipcode;

    /**
     * @return the street
     */
    public String getStreet() {
        return street;
    }

    /**
     * @param street the street to set
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the zipCode
     */
    public int getZipcode() {
        return zipcode;
    }

    /**
     * @param zipCode the zipCode to set
     */
    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }
}
