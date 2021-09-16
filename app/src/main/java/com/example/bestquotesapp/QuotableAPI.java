package com.example.bestquotesapp;

import com.example.bestquotesapp.models.Response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface QuotableAPI {

    @GET("/quotes")
    Call<Response> getQuotesResponse();

    @GET("/quotes")
    Call<Response> getQuotesByAuthor(@Query("author") String author);

}
