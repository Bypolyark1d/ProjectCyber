package com.example.projectcyber.Recycler;

import androidx.navigation.NavType;

import java.io.Serializable;

public class ItemProduct implements Serializable
{
    private int id;
    private int quantity;
    private String name;
    private int price;
    private String image;
    private String deatails;

    public ItemProduct(int id, int quantity, String name, int price, String image, String deatails) {
        this.id = id;
        this.quantity = quantity;
        this.name = name;
        this.price = price;
        this.image = image;
        this.deatails = deatails;
    }
    public ItemProduct()
    {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDetails() {
        return deatails;
    }

    public void setDetails(String details) {
        this.deatails = details;
    }
}
