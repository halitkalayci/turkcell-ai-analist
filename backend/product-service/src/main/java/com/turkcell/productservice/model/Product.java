package com.turkcell.productservice.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    private String id;

    @Column(name = "name", nullable = false, unique = true, length = 150)
    private String name;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "currency", nullable = false, length = 1)
    private String currency;

    public Product() {
        this.id = UUID.randomUUID().toString();
    }

    public Product(String name, Double price, String currency) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.price = price;
        this.currency = currency;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
