/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.json.bind.annotation.JsonbProperty;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author cop4856
 */
@XmlRootElement
@Entity
@Table(name = "DVDS")
@NamedQueries({
    @NamedQuery(name = "DVDS.findAll", query = "SELECT d FROM DVDS d"),
    @NamedQuery(name = "DVDS.findByDVDId", query = "SELECT d FROM DVDS d WHERE d.id = :dVDId"),
    @NamedQuery(name = "DVDS.findByDVDTitle", query = "SELECT d FROM DVDS d WHERE d.title = :dVDTitle"),
    @NamedQuery(name = "DVDS.findByDVDFormat", query = "SELECT d FROM DVDS d WHERE d.format = :dVDFormat"),
    @NamedQuery(name = "DVDS.findByDVDGenre", query = "SELECT d FROM DVDS d WHERE d.genre = :dVDGenre")})
public class DVDS implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "DVDId")
    @XmlAttribute
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "DVDTitle")
    private String title;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "DVDFormat")
    private String format;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "DVDGenre")
    private String genre;

    public DVDS() {
    }

    public DVDS(Integer dVDId) {
        this.id = dVDId;
    }

    public DVDS(Integer dVDId, String dVDTitle, String dVDFormat, String dVDGenre) {
        this.id = dVDId;
        this.title = dVDTitle;
        this.format = dVDFormat;
        this.genre = dVDGenre;
    }

    public Integer getId() {
        return id;
    }

    public void setDVDId(Integer dVDId) {
        this.id = dVDId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String dVDTitle) {
        this.title = dVDTitle;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String dVDFormat) {
        this.format = dVDFormat;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String dVDGenre) {
        this.genre = dVDGenre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DVDS)) {
            return false;
        }
        DVDS other = (DVDS) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.jpamultiservlet.Dvds[ dVDId=" + id + " " + title + " ]";
    }

}
