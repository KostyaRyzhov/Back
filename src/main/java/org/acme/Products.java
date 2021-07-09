package org.acme;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "Products")
public class Products extends PanacheEntity {

    @Column(name = "product_id")
    private int product_id;

    @Column(name = "product_name")
    private String name;

    @Column(name = "product_price")
    private int product_price;

    @Column(name = "product_color")
    private String product_color;

    public Products() {
    }

    public Products(String name, int id, int product_price, String product_color) {
        this.name = name;
        this.product_id = id;
        this.product_price = product_price;
        this.product_color = product_color;
    }

    public int getId() {
        return product_id;
    }

    public void setId(int id) {
        this.product_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProduct_price() {
        return product_price;
    }

    public void setProduct_price(int product_price) {
        this.product_price = product_price;
    }

    public String getProduct_color() {
        return product_color;
    }

    public void setProduct_color(String product_color) {
        this.product_color = product_color;
    }
}
