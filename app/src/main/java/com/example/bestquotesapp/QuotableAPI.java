package com.example.bestquotesapp;

import com.example.bestquotesapp.models.QuotesResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;


public interface QuotableAPI {

    @GET("/quotes")
    Call<QuotesResponse> getQuotesResponse();

    @GET("/quotes")
    Call<QuotesResponse> getQuotesResponse(
            @QueryMap Map<String, String> options
    );

}
