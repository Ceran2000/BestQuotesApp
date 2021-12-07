package com.example.bestquotesapp.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.bestquotesapp.APIClient;
import com.example.bestquotesapp.QuotableAPI;
import com.example.bestquotesapp.models.QuotesResponse;

import java.io.IOException;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import rx.Observable;

public class QuotesRepository {

    private final QuotableAPI quotesApi;

    private static QuotesRepository quotesRepository;
    public static QuotesRepository getInstance(){
        if ( quotesRepository == null ){
            quotesRepository = new QuotesRepository();
        }
        return quotesRepository;
    }

    public QuotesRepository(){
        quotesApi = APIClient.getRetrofitInstance().create(QuotableAPI.class);
    }

    public Observable<QuotesResponse> getQuotes(Map<String, String> options){
        return quotesApi.getQuotesResponse(options);
    }
}