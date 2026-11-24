package com.example.examen_3_consumo_apirest_retrofit;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("products")
    Call<ProductResponse> getProducts();
}
