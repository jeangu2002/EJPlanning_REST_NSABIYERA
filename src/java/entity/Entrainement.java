/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.lang.annotation.Native;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author jean-gustave
 */
@Entity
@Table(name = "\"entrainement\"")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Entrainement.findAll", query = "SELECT e FROM Entrainement e")
    , @NamedQuery(name = "Entrainement.findById", query = "SELECT e FROM Entrainement e WHERE e.id = :id")
    ,@NamedQuery(name = "Entrainement.findUnconfirmed", query = "SELECT e FROM Entrainement e JOIN FETCH e.AffectationsMoniteurs as a WHERE a.statut = ?1")})
public class Entrainement implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Column(name = "\"id\"")
    @GeneratedValue(generator = "entrainement_pk_seq")
    @SequenceGenerator(name = "entrainement_pk_seq", sequenceName = "\"ENTRAINEMENT_SEQ\"", allocationSize = 1)
    private int id;
    @Column(name = "\"DATE_ENTRAINEMENT\"")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEntrainement;
    @Basic(optional = false)
    @Column(name = "\"statut\"")
    private int statut;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"id_groupe\"", nullable = true)
    private Groupe groupe;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "\"associer\"", joinColumns = {
        @JoinColumn(name = "\"id_entrainement\"", referencedColumnName = "\"id\"")}, inverseJoinColumns = {
        @JoinColumn(name = "\"id_sport\"", referencedColumnName = "\"id\"")})
    private Collection<Sport> sports;

    @JoinColumn(name = "\"ID_SAISON\"")
    @ManyToOne(fetch = FetchType.LAZY)
    private Saison saison;

    @JoinTable(name = "\"affecter\"", joinColumns = {
        @JoinColumn(name = "\"id_entrainement\"", referencedColumnName = "\"id\"")}, inverseJoinColumns = {
        @JoinColumn(name = "\"id_moniteur\"", referencedColumnName = "\"id\"")})
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "entrainement", fetch = FetchType.LAZY)
    private Collection<Affecter> AffectationsMoniteurs;

    public Entrainement() {
    }

    public Entrainement(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateEntrainement() {
        return dateEntrainement;
    }

    public void setDateEntrainement(Date dateEntrainement) {
        this.dateEntrainement = dateEntrainement;
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }

    public Groupe getGroupe() {
        return groupe;
    }

    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }

    public Collection<Sport> getSports() {
        return sports;
    }

    public void setSports(Collection<Sport> sport) {
        this.sports = sport;
    }

    @JsonIgnore
    @XmlTransient
    public Collection<Affecter> getAffectationsMoniteurs() {
        return AffectationsMoniteurs;
    }

    public void setAffectationsMoniteurs(Collection<Affecter> AffectationsMoniteurs) {
        this.AffectationsMoniteurs = AffectationsMoniteurs;
    }

    public Saison getSaison() {
        return saison;
    }

    public void setSaison(Saison saison) {
        this.saison = saison;
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
        if (!(object instanceof Entrainement)) {
            return false;
        }
        Entrainement other = (Entrainement) object;
//        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Entrainement[ id=" + id + " ]";
    }

}
