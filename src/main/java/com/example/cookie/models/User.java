package com.example.cookie.models;

import javax.persistence.*;

import static javax.persistence.CascadeType.ALL;

@Entity(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "citycode")
    private String citycode;

    @Column(name = "password")
    private String password;

    @Column(name = "confirmpassword")
    private String confirmpassword;

    @Column(name = "wallet")
    private int wallet;

    @OneToOne(cascade=ALL)
    ShopBag shopbag;

    public User() {
    }

    public User(String name, String lastname, String email, String phone, String address, String citycode, String password, String confirmpassword, int wallet) {
        this.firstname = name;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.citycode = citycode;
        this.password = password;
        this.confirmpassword = confirmpassword;
        this.wallet = wallet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmpassword() {
        return confirmpassword;
    }

    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }

    public ShopBag getShopbag() {
        return shopbag;
    }

    public void setShopbag(ShopBag shopbag) {
        this.shopbag = shopbag;
    }

    public int getWallet() {
        return wallet;
    }

    public void setWallet(int wallet) { this.wallet = wallet; }
}