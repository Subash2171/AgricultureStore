package com.subash.agriculturestore.model;

public class Product {
    //private int id;
    private String name;
    private String image;
    private String description;
    private float price;
    private int discount_rate;
    private float rating;

    public Product(String name, String image, String description, float price, int discount_rate, float rating) {
        this.name = name;
        this.image = image;
        this.description = description;
        this.price = price;
        this.discount_rate = discount_rate;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getDiscount_rate() {
        return discount_rate;
    }

    public void setDiscount_rate(int discount_rate) {
        this.discount_rate = discount_rate;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
