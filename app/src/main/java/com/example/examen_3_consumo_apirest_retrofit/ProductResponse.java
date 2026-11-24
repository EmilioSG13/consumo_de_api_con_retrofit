package com.example.examen_3_consumo_apirest_retrofit;

import java.util.List;

public class ProductResponse {
    private List<Product> products;
    private int total;
    private int skip;
    private int limit;

    public List<Product> getProducts() {
        return products;
    }
}
