package com.example.bestquotesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ReceiverCallNotAllowedException;
import android.os.Bundle;
import android.widget.Toast;

import com.example.bestquotesapp.models.Quote;
import com.example.bestquotesapp.models.Response;
import com.example.bestquotesapp.ui.QuotesAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    //private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*findViews();

        QuotableAPI quotableAPI = APIClient.getRetrofitInstance().create(QuotableAPI.class);
        Call<Response> call = quotableAPI.getQuotesResponse();
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                generateQuotesList(response.body().getResults());
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Can't load data...", Toast.LENGTH_LONG).show();
            }
        });*/
    }

    /*private void findViews(){ recyclerView = findViewById(R.id.recyclerViewQuotes); }*/

    /*private void generateQuotesList(List<Quote> quotesList) {
        QuotesAdapter quotesAdapter = new QuotesAdapter(quotesList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(quotesAdapter);
    }*/
}