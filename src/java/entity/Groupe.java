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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
@Table(name = "\"groupe\"")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Groupe.findAll", query = "SELECT g FROM Groupe g")
    , @NamedQuery(name = "Groupe.findById", query = "SELECT g FROM Groupe g WHERE g.id = :id")
    , @NamedQuery(name = "Groupe.findByNom", query = "SELECT g FROM Groupe g WHERE g.nom = :nom")})
public class Groupe implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Column(name = "\"id\"")
    @GeneratedValue(generator="groupe_pk_seq")
    @SequenceGenerator(name = "groupe_pk_seq",sequenceName = "GROUPE_SEQ",allocationSize = 1)
    private int id;
    @Basic(optional = false)
    @Column(name = "\"nom\"")
    private String nom;
    @JoinTable(name = "\"participer\"", joinColumns = {
        @JoinColumn(name = "\"id_groupe\"", referencedColumnName = "\"id\"")}, inverseJoinColumns = {
        @JoinColumn(name = "\"id_entrainement\"", referencedColumnName = "\"id\"")})
    @OneToMany(mappedBy = "groupe", fetch = FetchType.LAZY)
    private Collection<Entrainement> entrainementCollection;
    @JoinTable(name = "\"disputer\"", joinColumns = {
        @JoinColumn(name = "\"id_groupe\"", referencedColumnName = "\"id\"")}, inverseJoinColumns = {
        @JoinColumn(name = "\"id_competition\"", referencedColumnName = "\"id\"")})
    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<Competition> competitionCollection;

    public Groupe() {
    }

    public Groupe(int id) {
        this.id = id;
    }

    public Groupe(int id, String nom) {
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

    @XmlTransient
    @JsonIgnore
    public Collection<Competition> getCompetitionCollection() {
        return competitionCollection;
    }

    public void setCompetitionCollection(Collection<Competition> competitionCollection) {
        this.competitionCollection = competitionCollection;
    }


    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Groupe)) {
            return false;
        }
        Groupe other = (Groupe) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Groupe[ id=" + id + " ]";
    }
    
}
