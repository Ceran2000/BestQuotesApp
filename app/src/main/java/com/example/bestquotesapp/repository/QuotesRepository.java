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

public class QuotesRepository {

    private static QuotesRepository quotesRepository;
    public static QuotesRepository getInstance(){
        if ( quotesRepository == null ){
            quotesRepository = new QuotesRepository();
        }
        return quotesRepository;
    }

    private final QuotableAPI quotesApi;

    public QuotesRepository(){
        quotesApi = APIClient.getRetrofitInstance().create(QuotableAPI.class);
    }

    //TODO: do zmiany (Mutable)
    public MutableLiveData<QuotesResponse> getQuotesWithoutOptions(){
        MutableLiveData<QuotesResponse> quotes = new MutableLiveData<>();
        quotesApi.getQuotesResponse().enqueue(new Callback<QuotesResponse>() {
            @Override
            public void onResponse(Call<QuotesResponse> call, retrofit2.Response<QuotesResponse> response) {
                if (response.isSuccessful()){
                    quotes.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<QuotesResponse> call, Throwable t) {
                Log.i("FAILURE: ", t.getMessage());
            }
        });
        return quotes;
    }

    public QuotesResponse getQuotes(Map<String, String> options) throws IOException {
        Call<QuotesResponse> call = quotesApi.getQuotesResponse(options);
        return call.execute().body();
    }
}