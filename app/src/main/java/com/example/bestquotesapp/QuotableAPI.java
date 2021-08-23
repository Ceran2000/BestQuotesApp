package com.example.bestquotesapp;

import com.example.bestquotesapp.models.Response;

import retrofit2.Call;
import retrofit2.http.GET;


public interface QuotableAPI {

    @GET("/quotes")
    Call<Response> getQuotesResponse();

}
