/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.service;

import dtos.EntrainementDTO;
import dtos.ToDto;
import entity.Entrainement;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author jean-gustave
 */
@javax.ejb.Stateless
@Path("entrainement")
public class EntrainementFacadeREST extends AbstractFacade<Entrainement> {

    //@PersistenceContext(unitName = "REAJ_REST_NSABIYERAPU")
    private EntityManager em = Persistence.createEntityManagerFactory("REAJ_REST_NSABIYERAPU").createEntityManager();

    public EntrainementFacadeREST() {
        super(Entrainement.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response create(Entrainement entity) {        
        return super.create(entity);
        
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") int id, Entrainement entity) {
        return super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") int id) {
        return super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Entrainement find(@PathParam("id") BigDecimal id) {
        return super.find(id);
    }

    @Override
    public List<Entrainement> findAll() 
    {
       try{
        return  super.findAll();
        }catch( Exception ex)
        {
            throw ex;
        }
    }
    
        @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getAll()
    {
        List<Entrainement> result = findAll();
        if(result != null && result.size() > 0)
        {
            GenericEntity<List<Entrainement>> entities = new GenericEntity<List<Entrainement>>(result){};
            return Response.ok(entities).build();
        }else
        {
            return Response.noContent().build();
        }
        
    }
    
    
        @GET
    @Path("candidature")
    public Response getAffectationsNonConfirmees()
    {
        Query query = em.createNamedQuery("Entrainement.findUnconfirmed",Entrainement.class);
        query.setParameter(1, 0);
        List<Entrainement> result = query.getResultList();
        if(result != null && result.size() > 1)
        {
            List<EntrainementDTO> dtos = ToDto.entrainmenetListToDTO(result);
            GenericEntity<List<EntrainementDTO>> entities = new GenericEntity<List<EntrainementDTO>>(dtos){};
            return Response.ok(entities).build();
        }else
        {
            return Response.noContent().build();
        }          
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Entrainement> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
