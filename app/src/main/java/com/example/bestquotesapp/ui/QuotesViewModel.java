package com.example.bestquotesapp.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bestquotesapp.models.Response;

public class QuotesViewModel extends ViewModel {
    private final MutableLiveData<Response> quotes = new MutableLiveData<>();
    public void setQuotes(Response response){
        quotes.setValue(response);
    }
    public LiveData<Response> getQuotes(){
        return quotes;
    }
}
