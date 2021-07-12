package org.acme;

import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.LinkedList;
import java.util.List;


@Path(GreetingResource.path)
public class GreetingResource {
    public static final String path = "/hello";
    private String string;
    private List<Products> objectLinkedList = new LinkedList<Products>();

    @Inject
    EntityManager em;


    @Context
    UriInfo uriInfo;

    @POST
    @Path("/post")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response getObject(@Valid Products products) {
            products.persist();
        return null;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Products> getAll() throws Exception {
        Query query = em.createQuery("select p from Products p where p.productName like '%'");
        return (List<Products>) query.getResultList();
    }

    @GET
    @Path("{id}/getOne")
    @Produces(MediaType.APPLICATION_JSON)
    public Products getOne(@PathParam Long id) {
        Products p = em.find(Products.class, id);
        return p;
    }

    @GET
    @Path("/find")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Products> find(@Valid Products products) {
        Query query = em.createQuery("select p from Products p where p.productId = ?2 and p.productPrice = ?3 or p.productName like ?1 and p.productColor like ?4");
        query.setParameter(1, "%"+products.getProductName()+"%");
        query.setParameter(2, products.getId());
        query.setParameter(3, products.getProductPrice());
        query.setParameter(4, "%"+products.getProductColor()+"%");
        return (List<Products>)query.getResultList();
    }
    @POST
    @Path("/delete")
    @Transactional
    public void delSubStr(@Valid Long id) {
        Products p = em.find(Products.class, id);
        em.remove(p);
    }

    @POST
    @Path("/update/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public void updateElement(@Valid Products products, @PathParam Long id){
        //Products p = getOne(Long.valueOf(products.getId()));
        Products p = getOne(id);
        p.setId(products.getId());
        p.setProductName(products.getProductName());
        p.setProductPrice(products.getProductPrice());
        p.setProductColor(products.getProductColor());
        //p.persist();
        em.persist(p);
    }
}
