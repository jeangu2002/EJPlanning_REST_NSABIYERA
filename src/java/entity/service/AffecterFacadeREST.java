/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.service;

import entity.Affecter;
import entity.AffecterPK;
import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author jean-gustave
 */
@Stateless
@Path("affecter")
public class AffecterFacadeREST extends AbstractFacade<Affecter> {

    //@PersistenceContext(unitName = "REAJ_REST_NSABIYERAPU")
    private EntityManager em = Persistence.createEntityManagerFactory("REAJ_REST_NSABIYERAPU").createEntityManager();

    private AffecterPK getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;idEntrainement=idEntrainementValue;idMoniteur=idMoniteurValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        entity.AffecterPK key = new entity.AffecterPK();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> idEntrainement = map.get("idEntrainement");
        if (idEntrainement != null && !idEntrainement.isEmpty()) {
            key.setIdEntrainement(new java.math.BigInteger(idEntrainement.get(0)));
        }
        java.util.List<String> idMoniteur = map.get("idMoniteur");
        if (idMoniteur != null && !idMoniteur.isEmpty()) {
            key.setIdMoniteur(new java.math.BigInteger(idMoniteur.get(0)));
        }
        return key;
    }

    public AffecterFacadeREST() {
        super(Affecter.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response create(Affecter entity) {
        return super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") int id, Affecter entity) {
        return super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") PathSegment id) {
        entity.AffecterPK key = getPrimaryKey(id);
        super.remove(super.find(key));
    }

    /**
     *
     * @param id
     * @return
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Affecter find(@PathParam("id") PathSegment id) {

        entity.AffecterPK key = getPrimaryKey(id);
        Affecter entity = super.find(key);
        return entity;

    }

     @Override
    public List<Affecter> findAll() {
   
        return super.findAll();
    }
    
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getAll()
    {
        List<Affecter> result = findAll();
        if(result != null && result.size() > 0)
        {
            GenericEntity<List<Affecter>> entities = new GenericEntity<List<Affecter>>(result){};
            return Response.ok(entities).build();
        }else
        {
            return Response.noContent().build();
        }
        
    }
    
    /**
     *
     * @param affectations
     */
    @POST
    @Path("candidature")
    public Response addMany(List<Affecter> affectations)
    {
        if(affectations != null)
        {
            em.getTransaction().begin();
            for(Affecter affecter : affectations)
            {
                em.persist(affecter);
                em.flush();
                em.clear();
            }
            
            em.getTransaction().commit();
            return Response.status(Response.Status.OK).build();
        }
        
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    
     @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("affectation/{idMoniteur}")
    public Response getConfirmedtrainings(@PathParam("idMoniteur") int idMoniteur)
    {
         TypedQuery<Affecter> query = em.createNamedQuery("Affecter.findConfirmed",Affecter.class);
        query.setParameter("statut", 1);
        query.setParameter("idMoniteur", idMoniteur);
        return getAffectations(query);
    }
    
    @GET
    @Path("affectation")
    public Response getAffectationsNonConfirmees()
    {
        TypedQuery<Affecter> query = em.createNamedQuery("Affecter.findByStatut",Affecter.class);
        query.setParameter("statut", 0);
       return getAffectations(query);
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Affecter> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    private Response getAffectations( TypedQuery query)
    {
         
        List<Affecter> result = query.getResultList();
        if(result != null && result.size() > 1)
        {
            GenericEntity<List<Affecter>> entities = new GenericEntity<List<Affecter>>(result){};
            return Response.ok(entities).build();
        }else
        {
            return Response.noContent().build();
        }          
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
