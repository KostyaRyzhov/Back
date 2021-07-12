package org.acme;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Products")
public class Products extends PanacheEntity {

    @Column(name = "product_id")
    private int productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_price")
    private int productPrice;

    @Column(name = "product_color")
    private String productColor;

    public Products() {
    }

    public Products(String productName, int productId, int productPrice, String productColor) {
        this.productName = productName;
        this.productId = productId;
        this.productPrice = productPrice;
        this.productColor = productColor;
    }

    public int getId() {
        return productId;
    }

    public void setId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductColor() {
        return productColor;
    }

    public void setProductColor(String productColor) {
        this.productColor = productColor;
    }
}
