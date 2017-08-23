/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.service;

import entity.Saison;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
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
@Stateless
@Path("saison")
public class SaisonFacadeREST extends AbstractFacade<Saison> {

   //@PersistenceContext(unitName = "REAJ_REST_NSABIYERAPU")
    private EntityManager em = Persistence.createEntityManagerFactory("REAJ_REST_NSABIYERAPU").createEntityManager();

    public SaisonFacadeREST() {
        super(Saison.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response create(Saison entity) {
        return super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") BigDecimal id, Saison entity) {
        return super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") BigDecimal id) {
        return super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Saison find(@PathParam("id") BigDecimal id) {
        return super.find(id);
    }


    @Override
    public List<Saison> findAll() {
        return super.findAll();
    }
    
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getAll()
    {
        List<Saison> result = findAll();
        if(result != null && result.size() > 0)
        {
            GenericEntity<List<Saison>> entities = new GenericEntity<List<Saison>>(result){};
            return Response.ok(entities).build();
        }else
        {
            return Response.noContent().build();
        }
        
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Saison> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
