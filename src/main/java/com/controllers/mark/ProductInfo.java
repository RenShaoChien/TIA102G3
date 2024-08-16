package com.controllers.mark;

import java.io.Serializable;

public class ProductInfo implements Serializable {
    private String id;
    private int quantity;
    private String name;
    private int price;
     
    public ProductInfo(String id, int quantity, String name, int price) {
        super();
        this.id = id;
        this.quantity = quantity;
        this.name = name;
        this.price = price;
    }
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    
    
}
