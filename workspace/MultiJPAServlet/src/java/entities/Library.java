/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.ArrayList;
import java.util.List;
import javax.json.bind.JsonbBuilder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "library")
public class Library {

    private List<DVDS> dvd;

    public Library() {
        dvd = new ArrayList<>();
    }

    public void add(DVDS d) {
        dvd.add(d);
    }

    public String toJSON() {
        return JsonbBuilder.create().toJson(dvd);
    }
}
