package com.example.bestquotesapp.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bestquotesapp.APIClient;
import com.example.bestquotesapp.MainActivity;
import com.example.bestquotesapp.QuotableAPI;
import com.example.bestquotesapp.R;
import com.example.bestquotesapp.models.Quote;
import com.example.bestquotesapp.models.Response;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;


public class QuotesFragment extends Fragment {

    private RecyclerView recyclerView;
    private FloatingActionButton fab;

    public QuotesFragment() {
        super(R.layout.fragment_quotes);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_quotes, container, false);

        findViews(root);
        setListeners();

        QuotableAPI quotableAPI = APIClient.getRetrofitInstance().create(QuotableAPI.class);
        Call<Response> call = quotableAPI.getQuotesResponse();
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                generateQuotesList(response.body().getResults());
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Toast.makeText(getContext(), "Can't load data...", Toast.LENGTH_LONG).show();
            }
        });


        return root;
    }

    private void findViews(View root){
        recyclerView = root.findViewById(R.id.recyclerViewQuotes);
        fab = root.findViewById(R.id.fab);
    }

    private void setListeners(){
        fab.setOnClickListener(v -> {
            new FilterDialogFragment().show(getChildFragmentManager(), FilterDialogFragment.TAG);
        });
    }

    private void generateQuotesList(List<Quote> quotesList) {
        QuotesAdapter quotesAdapter = new QuotesAdapter(quotesList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(quotesAdapter);
    }
}