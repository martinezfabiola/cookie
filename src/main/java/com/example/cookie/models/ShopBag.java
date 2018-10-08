package com.example.cookie.models;

import javax.persistence.*;

@Entity(name = "shopbag")
public class ShopBag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "total")
    private int total;

    public  ShopBag(){
    }

    public ShopBag(int total) {
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "ShopBag{" +
                "id=" + id +
                ", total=" + total +
                '}';
    }
}