package com.dreamshop.model;


import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "orders")
public class Order implements Serializable
{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;


    @OneToMany
    @Cascade(CascadeType.ALL)
    private List<OrderedItem> items;

    @Column(name = "totalPrice")
    private int totalPrice;


    @ManyToOne
    private Address shippingAddress;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<OrderedItem> getItems() {
        return items;
    }

    public void setItems(List<OrderedItem> items) {
        this.items = items;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
}
