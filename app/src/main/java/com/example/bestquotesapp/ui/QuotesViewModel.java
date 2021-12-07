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

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class QuotesViewModel extends ViewModel {

    private final QuotesRepository repository;
    private final MutableLiveData<QuotesResponse> quotes;
    private final MutableLiveData<Map<String, String>> options;

    private Subscription subscription;

    public QuotesViewModel(){
        repository = QuotesRepository.getInstance();
        options = new MutableLiveData<>();
        options.setValue(new HashMap<>());
        quotes = new MutableLiveData<>();

    }

    public LiveData<QuotesResponse> getQuotes(){
        if (quotes.getValue() == null){
            fetchQuotesFromServer();
        }
        return quotes;
    }

    public void fetchQuotesFromServer(){
        subscription = repository
                .getQuotes(options.getValue())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<QuotesResponse>() {
                    @Override
                    public void onCompleted() {
                        Log.d("VIEW MODEL", "onCompleted()");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("VIEW MODEL", "onError()");
                    }

                    @Override
                    public void onNext(QuotesResponse quotesResponse) {
                        Log.d("VIEW MODEL", "onNext()");
                        quotes.postValue(quotesResponse);
                    }
                });
    }

    public LiveData<Map<String, String>> getOptions(){
        return options;
    }

    public void addOption(String key, String value){
        if (options.getValue() != null)
            options.getValue().put(key, value);
    }

    public void clearOptions(){
        if (options.getValue() != null)
            options.getValue().clear();
    }

    public void unSubscribe() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
