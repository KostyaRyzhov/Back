package org.acme;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import java.util.List;

public class ProductRepository implements PanacheRepository<Products> {
    List<Products> finalByLastName(String lastName) {
        return list("lastName",lastName);
    }
}
