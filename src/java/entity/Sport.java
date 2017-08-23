/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author jean-gustave
 */
@Entity
@Table(name = "\"Sport\"")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sport.findAll", query = "SELECT s FROM Sport s")
    , @NamedQuery(name = "Sport.findById", query = "SELECT s FROM Sport s WHERE s.id = :id")
    , @NamedQuery(name = "Sport.findByNom", query = "SELECT s FROM Sport s WHERE s.nom = :nom")})
public class Sport implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Column(name = "\"id\"")
    @GeneratedValue(generator = "sport_pk_seq")
    @SequenceGenerator(name = "sport_pk_seq", sequenceName = "SPORT_SEQ",allocationSize = 1)
    private int id;
    @Basic(optional = false)
    @Column(name = "\"nom\"")
    private String nom;
    @ManyToMany(mappedBy = "sports", cascade = CascadeType.PERSIST)
    private Collection<Entrainement> entrainementCollection;

    public Sport() {
    }

    public Sport(int id) {
        this.id = id;
    }

    public Sport(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Entrainement> getEntrainementCollection() {
        return entrainementCollection;
    }

    public void setEntrainementCollection(Collection<Entrainement> entrainementCollection) {
        this.entrainementCollection = entrainementCollection;
    }
//
//    @Override
//    public int hashCode() {
//        int hash = 0;
//        hash += (id != null ? id.hashCode() : 0);
//        return hash;
//    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sport)) {
            return false;
        }
        Sport other = (Sport) object;
        if (this.id != other.id ) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Sport[ id=" + id + " ]";
    }
    
}
