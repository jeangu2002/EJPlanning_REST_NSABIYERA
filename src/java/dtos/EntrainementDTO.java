/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entity.Entrainement;
import entity.Groupe;
import entity.Saison;
import entity.Sport;
import entity.Utilisateur;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author jean-gustave
 */
@XmlRootElement(name = "entrainement")
public class EntrainementDTO {
    private static final long serialVersionUID = 1L;
   
    private int id;
    private Date dateEntrainement;
    private int statut;

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
    
    private Groupe groupe;

    private Collection<Utilisateur> utilisateurCollection;
    private Collection<Sport> sports;
    private Saison saison;
    @XmlTransient
    @JsonIgnore
    private Collection<Utilisateur> moniteurs;
    
    private Collection<Entrainement> entrainementCollection;


    public EntrainementDTO() {
        utilisateurCollection= new ArrayList<Utilisateur>();
    }
    
    
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Groupe getGroupe() {
        return groupe;
    }

    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }
    
    
    public Collection<Utilisateur> getUtilisateurCollection() {
        return utilisateurCollection;
    }

    public void setUtilisateurCollection(Collection<Utilisateur> utilisateurCollection) {
        this.utilisateurCollection = utilisateurCollection;
    }

    public Collection<Sport> getSports() {
        return sports;
    }

    /**
     *
     * @param sport
     */

    public Collection<Utilisateur> getMoniteurs() {
        return this.moniteurs;
    }

    public void setMoniteurs(Collection<Utilisateur> moniteurs) {
        this.moniteurs = moniteurs;
    }
     @XmlTransient
    public Collection<Entrainement> getEntrainementCollection() {
        return entrainementCollection;
    }

    public void setEntrainementCollection(Collection<Entrainement> entrainementCollection) {
        this.entrainementCollection = entrainementCollection;
    }

    public Saison getSaison() {
        return saison;
    }

    public void setSaison(Saison saison) {
        this.saison = saison;
    }

    public void setSports(Collection<Sport> sport) {
        this.sports = sport;
    }
    
}
