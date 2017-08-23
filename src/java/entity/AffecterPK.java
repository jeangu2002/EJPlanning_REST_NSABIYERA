/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jean-gustave
 */

@Embeddable
public class AffecterPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "\"id_entrainement\"")
    private BigInteger idEntrainement;
    @Basic(optional = false)
    @NotNull
    @Column(name = "\"id_moniteur\"")
    private BigInteger idMoniteur;

    public AffecterPK() {
    }

    public AffecterPK(BigInteger idEntrainement, BigInteger idMoniteur) {
        this.idEntrainement = idEntrainement;
        this.idMoniteur = idMoniteur;
    }

    public BigInteger getIdEntrainement() {
        return idEntrainement;
    }

    public void setIdEntrainement(BigInteger idEntrainement) {
        this.idEntrainement = idEntrainement;
    }

    public BigInteger getIdMoniteur() {
        return idMoniteur;
    }

    public void setIdMoniteur(BigInteger idMoniteur) {
        this.idMoniteur = idMoniteur;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEntrainement != null ? idEntrainement.hashCode() : 0);
        hash += (idMoniteur != null ? idMoniteur.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AffecterPK)) {
            return false;
        }
        AffecterPK other = (AffecterPK) object;
        if ((this.idEntrainement == null && other.idEntrainement != null) || (this.idEntrainement != null && !this.idEntrainement.equals(other.idEntrainement))) {
            return false;
        }
        if ((this.idMoniteur == null && other.idMoniteur != null) || (this.idMoniteur != null && !this.idMoniteur.equals(other.idMoniteur))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.AffecterPK[ idEntrainement=" + idEntrainement + ", idMoniteur=" + idMoniteur + " ]";
    }
    
}
