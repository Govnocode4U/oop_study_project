package org.utils;

import javax.persistence.*;

@Entity
@Table(name = "services")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected int id;

    @Column(name = "full_name")
    protected String fullName;
    @Column(name = "price")
    protected String price;


    public Service() {

    }

    public Service(String fullName, String price) {
        this.fullName = fullName;
        this.price = price;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }

    public void setFullName(String newFullName) {
        fullName = newFullName;
    }

    public void setPrice(String newPrice) {
        price = newPrice;
    }



}
