package com.baibei.myservicespace.dto;

import com.baibei.myservicespace.models.Product;

public class ProductCard {

    private String name;
    private String description;
    private String image;
    private String category;
    private double price;

    public ProductCard() {
        name = "";
        description = "";
        image = "";
        category = "other";
        price = 0.0;
    }

    public ProductCard(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;

        this.description = "";
        this.image = "";
    }

    public ProductCard(String name, String description, String image,
                       String category, double price) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.category = category;
        this.price = price;
    }

    public ProductCard(Product product) {
        this.name = product.getName();
        this.description = product.getDescription();
        this.image = product.getImage();
        this.category = product.getCategory();
        this.price = product.getPrice();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
