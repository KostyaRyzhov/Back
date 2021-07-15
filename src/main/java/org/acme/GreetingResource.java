package org.acme;

import org.hibernate.Filter;
import org.hibernate.Session;
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
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Math.min;


@Path(GreetingResource.path)
public class GreetingResource {
    public static final String path = "/hello";
    private String string;
    private List<Products> objectLinkedList = new LinkedList<Products>();

    @Inject
    EntityManager em;

    @Inject
    Session session;

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
        query.setParameter(1, "%" + products.getProductName() + "%");
        query.setParameter(2, products.getId());
        query.setParameter(3, products.getProductPrice());
        query.setParameter(4, "%" + products.getProductColor() + "%");
        return (List<Products>) query.getResultList();
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
    public void updateElement(@Valid Products products, @PathParam Long id) {
        //Products p = getOne(Long.valueOf(products.getId()));
        Products p = getOne(id);
        p.setId(products.getId());
        p.setProductName(products.getProductName());
        p.setProductPrice(products.getProductPrice());
        p.setProductColor(products.getProductColor());
        //p.persist();
        em.persist(p);
    }

    /*@POST
    @Path("/filter")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public List<Products> filterH(@Valid Products products) {
        return null;
    };*/

    @POST
    @Path("/getPage")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Products> paging(PageRequest pageRequest) {
        Query query = em.createQuery("SELECT p from Products p WHERE p.id > 0");
        query.setFirstResult((pageRequest.getOffset()) * pageRequest.getElCount());
        query.setMaxResults(pageRequest.getElCount());
        return query.getResultList();
    }

    @POST
    @Path("/getCount")
    public Long getCount() {
        Query query = em.createQuery("SELECT count(*) FROM Products");
        return (Long) query.getResultList().get(0);
    }

    @POST
    @Path("{pgInfo}/filter")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public List<Products> filter(@Valid Products products, @PathParam String pgInfo) throws InterruptedException {
        String[] strMass = pgInfo.split("_");
        //ProductID
        if (strMass[0].charAt(0) == '>') {
            Filter filterI = session.enableFilter("productFilterMinId");
            filterI.setParameter("minId", products.getId());
        }
        if (strMass[0].charAt(0) == '<') {
            Filter filterI = session.enableFilter("productFilterMaxId");
            filterI.setParameter("maxId", products.getId());
        }
        if (strMass[0].charAt(0) == '=') {
            Filter filterI = session.enableFilter("productFilterId");
            filterI.setParameter("thisId", products.getId());
        }
        //ProductPrice
        if (strMass[1].charAt(0) == '>') {
            Filter filterI = session.enableFilter("productFilterMinP");
            filterI.setParameter("minPrice", products.getProductPrice());
        }
        if (strMass[1].charAt(0) == '<') {
            Filter filterI = session.enableFilter("productFilterMaxP");
            filterI.setParameter("maxPrice", products.getProductPrice());
        }
        if (strMass[1].charAt(0) == '=') {
            Filter filterI = session.enableFilter("productFilterPrice");
            filterI.setParameter("thisPrice", products.getProductPrice());
        }
        //ProductName
        Filter filterN = session.enableFilter("productFilterName");
        filterN.setParameter("thisName", products.getProductName());

        //ProductColor
        Filter filterC = session.enableFilter("productFilterColor");
        filterC.setParameter("thisColor", products.getProductColor());

        List<Products> list = session.createQuery("From Products").list();
        List<Products> result = new ArrayList<>();
        int count = Integer.parseInt(strMass[2]);
        int offset = Integer.parseInt(strMass[3]);
        int len = min((offset + 1) * count, list.size());
        for (int i = offset * count; i < len; i++) {
            result.add(list.get(i));
        }
        result.add(new Products("", list.size(), 0, ""));
        return result;
    }

}
