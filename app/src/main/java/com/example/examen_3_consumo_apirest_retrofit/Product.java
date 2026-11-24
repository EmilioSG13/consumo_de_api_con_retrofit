package com.example.examen_3_consumo_apirest_retrofit;

import java.io.Serializable;

public class Product implements Serializable {
    private int id;
    private String title;
    private String description;
    private String category;
    private double price;
    private String brand;
    private double rating;
    private String thumbnail;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public String getBrand() {
        return brand;
    }

    public double getRating() {
        return rating;
    }

    public String getThumbnail() {
        return thumbnail;
    }
}
