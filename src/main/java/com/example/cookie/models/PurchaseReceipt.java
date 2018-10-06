package com.example.cookie.models;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity(name = "purchase_receipt")
public class PurchaseReceipt {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "quantity")
    private int quantity;

    @ManyToOne(cascade=ALL)
    Product product;

    @ManyToOne(cascade=ALL)
    ShopBag shopbag;

    public PurchaseReceipt(){
    }

    public PurchaseReceipt(int quantity) {
        this.quantity = quantity;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ShopBag getShopbag() {
        return shopbag;
    }

    public void setShopbag(ShopBag shopbag) {
        this.shopbag = shopbag;
    }

}
