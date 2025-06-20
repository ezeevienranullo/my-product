package com.example.myproduct.service;

import com.example.myproduct.model.ProductResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("products")
    Call<ProductResponse> getProducts();
}
