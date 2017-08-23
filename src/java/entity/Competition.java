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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name = "\"competition\"")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Competition.findAll", query = "SELECT c FROM Competition c")
    , @NamedQuery(name = "Competition.findById", query = "SELECT c FROM Competition c WHERE c.id = :id")
    , @NamedQuery(name = "Competition.findByNom", query = "SELECT c FROM Competition c WHERE c.nom = :nom")
    , @NamedQuery(name = "Competition.findByDateCompetition", query = "SELECT c FROM Competition c WHERE c.dateCompetition = :dateCompetition")
    , @NamedQuery(name = "Competition.findByLieu", query = "SELECT c FROM Competition c WHERE c.rue = :rue")})
public class Competition implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Column(name = "\"id\"")
    @GeneratedValue(generator = "competition_pk_seq")
    @SequenceGenerator(name = "competition_pk_seq", sequenceName = "COMPETITION_SEQ",allocationSize = 1)
    private int id;
    @Basic(optional = false)
    @Column(name = "\"nom\"")
    private String nom;
    @Basic(optional = false)
    @Column(name = "\"date_competition\"")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCompetition;
    @Basic(optional = false)
    @Column(name = "\"rue\"")
    private String rue;
    @Column(name = "\"numero\"")
    private int numero;
    @Column(name = "\"code_postal\"")
    private int codePostal;
    @Column(name = "\"localite\"")
    private String localite;
    @Column(name = "\"statut\"")
    private int statut;
    @JoinColumn(name="\"ID_SAISON\"")
    @ManyToOne(fetch = FetchType.LAZY)
    private Saison saison;
    @JoinColumn(name="\"id_sport\"")
    @ManyToOne(fetch = FetchType.LAZY)
    private Sport sport;
    
    @JoinTable(name = "\"encadrer\"", joinColumns = {
        @JoinColumn(name = "\"ID_COMPETITION\"", referencedColumnName = "\"id\"")}, inverseJoinColumns = {
        @JoinColumn(name = "\"ID_MONITEUR\"", referencedColumnName = "\"id\"")})
    @ManyToMany
    private Collection<Utilisateur> utilisateurCollection;
    @ManyToMany(mappedBy = "competitionCollection")
    private Collection<Groupe> groupeCollection;
    
    

    public Competition() {
    }

    public Competition(int id) {
        this.id = id;
    }

    public Competition(int id, String nom, Date dateCompetition, String rue,
             int codePostal, int numero, String localite) {
        this.id = id;
        this.nom = nom;
        this.dateCompetition = dateCompetition;
        this.rue = rue;
        this.codePostal = codePostal;
        this.localite = localite;
        this.numero = numero;
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

    public Date getDateCompetition() {
        return dateCompetition;
    }

    public void setDateCompetition(Date dateCompetition) {
        this.dateCompetition = dateCompetition;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(int codePostal) {
        this.codePostal = codePostal;
    }

    public String getLocalite() {
        return localite;
    }

    public void setLocalite(String localite) {
        this.localite = localite;
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }

    public Saison getSaison() {
        return saison;
    }

    public void setSaison(Saison saison) {
        this.saison = saison;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }
    

    @XmlTransient
    @JsonIgnore
    public Collection<Utilisateur> getUtilisateurCollection() {
        return utilisateurCollection;
    }

    public void setUtilisateurCollection(Collection<Utilisateur> utilisateurCollection) {
        this.utilisateurCollection = utilisateurCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Groupe> getGroupeCollection() {
        return groupeCollection;
    }

    public void setGroupeCollection(Collection<Groupe> groupeCollection) {
        this.groupeCollection = groupeCollection;
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
        if (!(object instanceof Competition)) {
            return false;
        }
        Competition other = (Competition) object;
        if (this.id !=other.id  ) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Competition[ id=" + id + " ]";
    }
    
}
