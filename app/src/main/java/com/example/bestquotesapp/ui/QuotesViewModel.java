package com.example.bestquotesapp.ui;


import android.util.Log;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bestquotesapp.QuotableAPI;
import com.example.bestquotesapp.models.QuotesResponse;
import com.example.bestquotesapp.repository.QuotesRepository;


import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import retrofit2.Retrofit;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class QuotesViewModel extends ViewModel {

    private static final String TAG = "QuotesViewModel:";

    //@Inject
    QuotesRepository repository;
    private Subscription subscription;

    private final MutableLiveData<QuotesResponse> quotes;
    private final MutableLiveData<Map<String, String>> options;

    @Inject
    public QuotesViewModel(
          //  QuotesRepository repository
    ) {
        //this.repository = repository;
        options = new MutableLiveData<>();
        options.setValue(new HashMap<>());
        quotes = new MutableLiveData<>();

        Log.d(TAG, "QuotesViewModel created...");
    }

    public LiveData<QuotesResponse> getQuotes() {
        if (quotes.getValue() == null) {
            fetchQuotesFromServer();
        }
        return quotes;
    }

    public void fetchQuotesFromServer() {
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

    public LiveData<Map<String, String>> getOptions() {
        return options;
    }

    public void addOption(String key, String value) {
        if (options.getValue() != null)
            options.getValue().put(key, value);
    }

    public void clearOptions() {
        if (options.getValue() != null)
            options.getValue().clear();
    }

    public void unSubscribe() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
