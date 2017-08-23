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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
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
@Table(name = "\"utilisateur\"")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Utilisateur.findAll", query = "SELECT u FROM Utilisateur u")
    , @NamedQuery(name = "Utilisateur.findById", query = "SELECT u FROM Utilisateur u WHERE u.id = :id")
    , @NamedQuery(name = "Utilisateur.findByNom", query = "SELECT u FROM Utilisateur u WHERE u.nom = :nom")
    , @NamedQuery(name = "Utilisateur.findByPrenom", query = "SELECT u FROM Utilisateur u WHERE u.prenom = :prenom")
    , @NamedQuery(name = "Utilisateur.findByPseudo", query = "SELECT u FROM Utilisateur u WHERE u.pseudo = :pseudo")
    , @NamedQuery(name = "Utilisateur.findByMotDePasse", query = "SELECT u FROM Utilisateur u WHERE u.motDePasse = :motDePasse")
    , @NamedQuery(name = "Utilisateur.findByType", query = "SELECT u FROM Utilisateur u WHERE u.type = :type")
    , @NamedQuery(name = "Utilisateur.authenticate", query = "SELECT u FROM Utilisateur u WHERE u.pseudo = :pseudo AND u.motDePasse = :motDePasse")})
@NamedNativeQueries({
            @NamedNativeQuery(name="utilisateur.getMoniteursDisponibles", query="select \"id\", \"nom\", \"prenom\", \"pseudo\", \"motDePasse\", \"type\" from \"utilisateur\" u\n" +
                        " where not exists (select * from \"entrainement\" e\n" +
                        " join \"affecter\" a on a.\"id_entrainement\" = e.\"id\"\n" +
                        " join \"disponibilite\" d on d.\"id_moniteur\" = a.\"id_moniteur\"\n" +
                        " where d.\"date_fin\" <= e.\"DATE_ENTRAINEMENT\");",resultClass = Utilisateur.class)
            })
public class Utilisateur implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @NotNull
    @Column(name = "\"id\"")
    @GeneratedValue(generator = "utilisateur_pk_seq")
    @SequenceGenerator(name = "utilisateur_pk_seq", sequenceName = "UTILISATEUR_SEQ",allocationSize = 1)
    private int id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "\"nom\"")
    private String nom;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "\"prenom\"")
    private String prenom;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "\"pseudo\"")
    private String pseudo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "\"mot_de_passe\"")
    private String motDePasse;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "\"type\"")
    private String type;
    @OneToMany(mappedBy = "moniteur", fetch = FetchType.LAZY)
    private Collection<Disponibilite> disponibiliteCollection;
        
   
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "utilisateur")
    private Collection<Affecter> entrainementCollection;
    

    public Utilisateur() {
    }

    public Utilisateur(int id) {
        this.id = id;
    }

    public Utilisateur(int id, String nom, String prenom, String pseudo, String motDePasse, String type) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.pseudo = pseudo;
        this.motDePasse = motDePasse;
        this.type = type;
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Disponibilite> getDisponibiliteCollection() {
        return disponibiliteCollection;
    }

    public void setDisponibiliteCollection(Collection<Disponibilite> disponibiliteCollection) {
        this.disponibiliteCollection = disponibiliteCollection;
    }
    @JsonIgnore
    @XmlTransient
    public Collection<Affecter> getEntrainementCollection() {
        return entrainementCollection;
    }

    public void setEntrainementCollection(Collection<Affecter> entrainementCollection) {
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
        if (!(object instanceof Utilisateur)) {
            return false;
        }
        Utilisateur other = (Utilisateur) object;
        if (this.id != other.id ) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Utilisateur[ id=" + id + " ]";
    }
    
}
