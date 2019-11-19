/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.model;

import com.mycompany.entities.DVD;
import java.util.ArrayList;
import java.util.List;
import javax.json.bind.JsonbBuilder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "library")
public class Library {

    private List<DVD> dvd;

    public Library() {
        dvd = new ArrayList<>();
    }

    public void add(DVD d) {
        dvd.add(d);
    }

    public String toJSON() {
        return JsonbBuilder.create().toJson(dvd);
    }
}
