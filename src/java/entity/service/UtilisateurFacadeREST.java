/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.service;

import entity.Utilisateur;
import java.math.BigDecimal;
import java.util.Date;
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
@Path("utilisateur")
public class UtilisateurFacadeREST extends AbstractFacade<Utilisateur> {

    //@PersistenceContext(unitName = "REAJ_REST_NSABIYERAPU")
    private EntityManager em = Persistence.createEntityManagerFactory("REAJ_REST_NSABIYERAPU").createEntityManager();;

    public UtilisateurFacadeREST() {
        super(Utilisateur.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response create(Utilisateur entity) {
        return super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") BigDecimal id, Utilisateur entity) {
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
    public Utilisateur find(@PathParam("id") BigDecimal id) {
        return super.find(id);
    }


    @Override
    public List<Utilisateur> findAll() {
        return super.findAll();
    }
    
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getAll()
    {
        List<Utilisateur> result = findAll();
        if(result != null && result.size() > 0)
        {
            GenericEntity<List<Utilisateur>> entities = new GenericEntity<List<Utilisateur>>(result){};
            return Response.ok(entities).build();
        }else
        {
            return Response.noContent().build();
        }
        
    }

//    @GET
//    @Path("{from}/{to}")
//    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    public List<Utilisateur> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
//        return super.findRange(new int[]{from, to});
//    }

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
    @GET
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("{pseudo}/{password}")
    public Response authenticate(@PathParam("pseudo") String pseudo, @PathParam("password") String password)
    {
        List utilisateurs = getEntityManager().createNamedQuery("Utilisateur.authenticate")
                .setParameter("pseudo", pseudo).setParameter("motDePasse", password)
                .getResultList();
        if(utilisateurs == null || utilisateurs.size() == 0 )
            return Response.status(Response.Status.UNAUTHORIZED).build();
        
        Utilisateur utilisateur =(Utilisateur) utilisateurs.get(0);
        return Response.accepted(utilisateur).build();
    }
    @GET
    @Path("disponibilite/{date}")
    public Response GetMoniteurDispobnibles(@PathParam("date") String date)
    {
        return Response.accepted().build();
    }
   
}
