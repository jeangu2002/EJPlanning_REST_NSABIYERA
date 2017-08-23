/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author jean-gustave
 */
@Entity
@Table(name = "\"disponibilite\"")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Disponibilite.findAll", query = "SELECT d FROM Disponibilite d")
    , @NamedQuery(name = "Disponibilite.findById", query = "SELECT d FROM Disponibilite d WHERE d.id = :id")
    , @NamedQuery(name = "Disponibilite.findByDateDebut", query = "SELECT d FROM Disponibilite d WHERE d.dateDebut = :dateDebut")
    , @NamedQuery(name = "Disponibilite.findByDateFin", query = "SELECT d FROM Disponibilite d WHERE d.dateFin = :dateFin")})
public class Disponibilite implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @NotNull
    @Column(name = "\"id\"")
    @GeneratedValue(generator = "disponibilite_pk_seq")
    @SequenceGenerator(name = "disponibilite_pk_seq", sequenceName = "DISPONIBILITE_SEQ",allocationSize = 1)
    @JsonProperty
    private int id;
    @Column(name = "\"date_debut\"")
    @Temporal(TemporalType.DATE)
    @JsonProperty
    private Date dateDebut;
    @Column(name = "\"date_fin\"")
    @Temporal(TemporalType.DATE)
    @JsonProperty
    private Date dateFin;
    @JoinColumn(name = "\"id_moniteur\"", referencedColumnName = "\"id\"")
    @ManyToOne
    private Utilisateur moniteur;

    public Disponibilite() {
    }

    public Disponibilite(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Utilisateur getMoniteur() {
        return moniteur;
    }

    public void setMoniteur(Utilisateur moniteur) {
        this.moniteur = moniteur;
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
        if (!(object instanceof Disponibilite)) {
            return false;
        }
        Disponibilite other = (Disponibilite) object;
        if (this.id != other.id ) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Disponibilite[ id=" + id + " ]";
    }
    
}
