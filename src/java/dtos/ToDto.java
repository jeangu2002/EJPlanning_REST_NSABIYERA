/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entity.Affecter;
import entity.Entrainement;
import entity.Utilisateur;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jean-gustave
 */
public class ToDto {
    
    public static List<EntrainementDTO> entrainmenetListToDTO(List<Entrainement> entrainements)
    {
        if(entrainements == null)
            return null;
        
        List<EntrainementDTO> entrainementsDto = new ArrayList<>();
        for(Entrainement e : entrainements)
        {
            EntrainementDTO dto = new EntrainementDTO();
            dto.setId(e.getId());
            dto.setGroupe(e.getGroupe());
            dto.setSaison(e.getSaison());
            dto.setSports(e.getSports());
            dto.setStatut(e.getStatut());
            dto.setDateEntrainement(e.getDateEntrainement());
            for(Affecter a : e.getAffectationsMoniteurs())
            {
                a.getUtilisateur().setEntrainementCollection(null);
               dto.getUtilisateurCollection().add(a.getUtilisateur());
            }
            entrainementsDto.add(dto);
        }
        return entrainementsDto;
    }
       
  
}
