/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author jean-gustave
 */
@Entity
@Table(name = "\"saison\"")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Saison.findAll", query = "SELECT s FROM Saison s")
    , @NamedQuery(name = "Saison.findById", query = "SELECT s FROM Saison s WHERE s.id = :id")
    , @NamedQuery(name = "Saison.findByNom", query = "SELECT s FROM Saison s WHERE s.nom = :nom")
    , @NamedQuery(name = "Saison.findByDateDebut", query = "SELECT s FROM Saison s WHERE s.dateDebut = :dateDebut")
    , @NamedQuery(name = "Saison.findByDateFin", query = "SELECT s FROM Saison s WHERE s.dateFin = :dateFin")})
public class Saison implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @NotNull
    @Column(name = "\"id\"")
    @GeneratedValue(generator = "saison_pk_seq")
    @SequenceGenerator(name = "saison_pk_seq", sequenceName = "SAISON_SEQ",allocationSize = 1)
    private int id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "\"nom\"")
    private String nom;
    @Basic(optional = false)
    @NotNull
    @Column(name = "\"date_debut\"")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDebut;
    @Basic(optional = false)
    @NotNull
    @Column(name = "\"date_fin\"")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateFin;
    @XmlTransient
    @JsonIgnore
    @OneToMany(mappedBy = "saison")
    private Collection<Entrainement> entrainementCollection;

    public Saison() {
    }

    public Saison(int id) {
        this.id = id;
    }

    public Saison(int id, String nom, Date dateDebut, Date dateFin) {
        this.id = id;
        this.nom = nom;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
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

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }
    @XmlTransient
    @JsonIgnore
    public Collection<Entrainement> getEntrainementCollection() {
        return entrainementCollection;
    }

    public void setEntrainementCollection(Collection<Entrainement> entrainementCollection) {
        this.entrainementCollection = entrainementCollection;
    }
    
    
    

//    @Override
//    public int hashCode() {
//        int hash = 0;
//        hash += (id != null ? id.hashCode() : 0);
//        return hash;
//    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Saison)) {
            return false;
        }
        Saison other = (Saison) object;
        if (this.id != other.id ) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Saison[ id=" + id + " ]";
    }
    
}
