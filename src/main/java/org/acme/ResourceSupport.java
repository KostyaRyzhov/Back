package org.acme;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Page;

import javax.validation.Valid;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.function.Function;

public class ResourceSupport {

    public static Response getOne(Long id, PanacheRepository repository) {
        return getItem(id, repository, Function.identity());
    }

    public static Response getItem(Long id, PanacheRepository repository, Function itemExtractor) {
        var optional = repository.findByIdOptional(id);
        if (optional.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(itemExtractor.apply(optional.get())).build();
    }

    public static Response getAll(PageRequest pageRequest, PanacheRepository repository) {
        return Response.ok(repository.findAll()
                .page(Page.of(pageRequest.getOffset(), pageRequest.getElCount()))
                .list()).build();
    }

    public static Response create(@Valid Products products, PanacheRepository<Products> repository, UriInfo uriInfo) {

       URI location = uriInfo.getAbsolutePathBuilder()
                .path("{id}")
                .resolveTemplate("id", products.getId())
                .build();

        return null; //Response.created(location).build();
    }
}
