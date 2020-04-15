package com.example.assignment3quotes.Entities;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
//GETs the code from the API with category = dev
public interface QuotesService {
    @GET("jokes/random?category=dev")
    Call<QuotesResponse> getResponse();
}
