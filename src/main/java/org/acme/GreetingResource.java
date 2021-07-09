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
    public Response getObject(@Valid List<Products> products) {
        objectLinkedList = products;
        for (Products products1 : products) {
            products1.persist();
        }
        return null;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Products> getAll() throws Exception {
        Query query = em.createQuery("select p from Products p where p.name like '%'");
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
        Query query = em.createQuery("select p from Products p where p.product_id = ?2 and p.product_price = ?3 or p.name like ?1 and p.product_color like ?4");
        query.setParameter(1, "%"+products.getProduct_name()+"%");
        query.setParameter(2, products.getId());
        query.setParameter(3, products.getProduct_price());
        query.setParameter(4, "%"+products.getProduct_color()+"%");
        return (List<Products>)query.getResultList();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public void delSubStr() {

    }

    private String helloString() {
        return "hello";
    }
}
