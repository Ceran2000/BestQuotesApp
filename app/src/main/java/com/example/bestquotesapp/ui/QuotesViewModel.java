package com.example.bestquotesapp.ui;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bestquotesapp.models.QuotesResponse;
import com.example.bestquotesapp.repository.QuotesRepository;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class QuotesViewModel extends ViewModel {

    private final QuotesRepository repository;
    private final MutableLiveData<QuotesResponse> quotes;
    private final MutableLiveData<Map<String, String>> options;

    public QuotesViewModel(){
        repository = QuotesRepository.getInstance();
        options = new MutableLiveData<>();
        options.setValue(new HashMap<>());
        quotes = new MutableLiveData<>();
    }

    public LiveData<QuotesResponse> getQuotes(){
        if (quotes.getValue() == null){
            new QuotesAsyncTask().execute();
        }
        return quotes;
    }

    public void setQuotes(QuotesResponse quotesResponse){
        quotes.postValue(quotesResponse);
    }

    public void setOptions(String key, String value){
        Objects.requireNonNull(options.getValue()).put(key, value);
        new QuotesAsyncTask().execute();
    }

    public LiveData<Map<String, String>> getOptions(){ return options; }

    public void clearOptions(){
        options.getValue().clear();
    }


    class QuotesAsyncTask extends AsyncTask<Void, Void, Void>{

        @SafeVarargs
        @Override
        protected final Void doInBackground(Void... voids) {
            try {
                QuotesResponse quotesResponse = repository.getQuotes(options.getValue());
                if (quotesResponse != null)
                    setQuotes(quotesResponse);
                else Log.i("FAILED: ", "QUOTES RESPONSE IS NULL!");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

    }
}
