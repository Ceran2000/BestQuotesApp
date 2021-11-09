package com.example.bestquotesapp.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.bestquotesapp.R;
import com.example.bestquotesapp.models.Quote;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.Objects;


public class QuotesFragment extends Fragment {

    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private LinearLayout pagesListLayout;
    private QuotesViewModel viewModel;

    public QuotesFragment() {
        super(R.layout.fragment_quotes);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("FRAGMENT: ", "onCreate");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_quotes, container, false);

        findViews(root);
        setListeners();

        viewModel = new ViewModelProvider(requireActivity()).get(QuotesViewModel.class);
        viewModel.getQuotes().observe(getViewLifecycleOwner(), quotes ->{
            if (quotes != null) {
                if (quotes.getCount() != 0) {
                    Log.i("GENEROWANIE CYTATOW: ", quotes.getResults().get(0).toString());
                    generateQuotesList(quotes.getResults());
                    generatePages();
                }
            }
        });

        return root;
    }

    private void findViews(View root){
        recyclerView = root.findViewById(R.id.recyclerViewQuotes);
        fab = root.findViewById(R.id.fab);
        pagesListLayout = root.findViewById(R.id.pagesListLayout);
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

    private void generatePages(){
        pagesListLayout.removeAllViews();
        int totalPages = Objects.requireNonNull(viewModel.getQuotes().getValue()).getTotalPages();

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);

        int i = 1;
        if ( totalPages > 1 && totalPages <= 5 ) {
            Log.i("WESZLISMY W IFA: ", "1");
            params.weight = 1.0f / totalPages;
            while ( i <= totalPages ){
                Button button = new Button(getContext());
                button.setTag(i);
                button.setText(String.valueOf(i));
                button.setWidth(0);
                button.setLayoutParams(params);
                button.setOnClickListener(v -> {
                    Log.i("PAGE: ", button.getTag().toString());
                });
                pagesListLayout.addView(button);
                i++;
            }
        } else if ( totalPages > 5){
            Log.i("WESZLISMY W IFA: ", "2");
            params.weight = 1.0f / 7.0f;
            while ( i <= totalPages ){
                Button button = new Button(getContext());
                button.setTag(i);
                button.setText(String.valueOf(i));
                button.setWidth(0);
                button.setLayoutParams(params);
                button.setOnClickListener(v -> {
                    Log.i("PAGE: ", button.getTag().toString());
                });
                pagesListLayout.addView(button);
                Log.i("CREATED PAGE: ", String.valueOf(i));
                if ( i == 5) i = totalPages;
                else i++;
            }
            Button button = new Button(getContext());
            button.setTag("...");
            button.setText("...");
            button.setWidth(0);
            button.setLayoutParams(params);
            button.setOnClickListener(v -> {
                Log.i("PAGE: ", button.getTag().toString());
            });
            pagesListLayout.addView(button);
        }
    }
}