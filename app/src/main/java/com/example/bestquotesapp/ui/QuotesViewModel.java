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
    private MutableLiveData<QuotesResponse> quotes;
    private final MutableLiveData<Map<String, String>> options;

    public QuotesViewModel(){
        repository = QuotesRepository.getInstance();
        options = new MutableLiveData<>();
        options.setValue(new HashMap<>());
    }

    public LiveData<QuotesResponse> getQuotes(){
        if (quotes == null){
            Log.i("QUOTES ARE: ", "NULL");
            quotes = repository.getQuotesWithoutOptions();
        }
        return quotes;
    }

    public void setQuotes(QuotesResponse quotesResponse){
        quotes.postValue(quotesResponse);
    }

    public void setOptions(String key, String value){
        Objects.requireNonNull(options.getValue()).put(key, value);
        new QuotesAsyncTask().execute(options.getValue());
    }
    public LiveData<Map<String, String>> getOptions(){ return options; }

    public void clearOptions(){
        options.getValue().clear();
    }


    class QuotesAsyncTask extends AsyncTask<Map<String, String>, Void, Void>{

        @SafeVarargs
        @Override
        protected final Void doInBackground(Map<String, String>... optionsMap) {
            try {
                QuotesResponse quotesResponse = repository.getQuotes(optionsMap[0]);
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
