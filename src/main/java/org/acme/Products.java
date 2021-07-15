package org.acme;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.ParamDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Products")
@FilterDef(name="productFilterId", parameters={
        @ParamDef( name="thisId", type="integer" ),
})
@FilterDef(name="productFilterMinId", parameters={
        @ParamDef( name="minId", type="integer" )
})
@FilterDef(name="productFilterMaxId", parameters={
        @ParamDef( name="maxId", type="integer" )
})
@FilterDef(name = "productFilterName", parameters = {
        @ParamDef(name = "thisName", type="string")
})
@FilterDef(name = "productFilterPrice", parameters = {
        @ParamDef(name = "thisPrice", type="integer"),
})
@FilterDef(name = "productFilterMaxP", parameters = {
        @ParamDef( name="maxPrice", type="integer" ),
})
@FilterDef(name = "productFilterMinP", parameters = {
        @ParamDef( name="minPrice", type="integer" ),
})
@FilterDef(name = "productFilterColor", parameters = {
        @ParamDef(name = "thisColor", type="string")
})
@Filters( {
        @Filter(name = "productFilterId", condition = ":thisId = product_id"),
        @Filter(name = "productFilterMinId", condition = ":minId < product_id"),
        @Filter(name = "productFilterMaxId", condition = ":maxId > product_id"),
        @Filter(name = "productFilterName", condition = "product_name like :thisName"),
        @Filter(name = "productFilterPrice", condition = ":thisPrice = product_price"),
        @Filter(name = "productFilterMinP", condition = ":minPrice < product_price"),
        @Filter(name = "productFilterMaxP", condition = ":maxPrice > product_price"),
        @Filter(name = "productFilterColor", condition = "product_color like :thisColor")
} )
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
