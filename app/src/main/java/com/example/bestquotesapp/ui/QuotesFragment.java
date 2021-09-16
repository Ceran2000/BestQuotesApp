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
    private Button refresh;

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

        Log.i("FRAGMENT: ", "onCreateView");

        return root;
    }

    //TODO: onCreateView czy onViewCreated?
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(QuotesViewModel.class);
        viewModel.getQuotes().observe(getViewLifecycleOwner(), quotes ->{
            if(quotes.getCount() != 0 && quotes != null) {
                Log.i("GENEROWANIE CYTATOW: ", quotes.getResults().get(0).toString());
                generateQuotesList(quotes.getResults());
            }
        });

        Log.i("FRAGMENT: ", "onViewCreated");
    }

    private void findViews(View root){
        recyclerView = root.findViewById(R.id.recyclerViewQuotes);
        fab = root.findViewById(R.id.fab);
        refresh = root.findViewById(R.id.button_refresh);
    }

    private void setListeners(){

        fab.setOnClickListener(v -> {
            new FilterDialogFragment().show(getChildFragmentManager(), FilterDialogFragment.TAG);
        });

        refresh.setOnClickListener(v -> {
            if (viewModel.getQuotes().getValue() != null) {
                Log.i("REFRESH: ", viewModel.getQuotes().getValue().getResults().get(0).toString());
            } else {
                Log.i("REFRESH: ", "null");
            }
        });
    }

    private void generateQuotesList(List<Quote> quotesList) {
        QuotesAdapter quotesAdapter = new QuotesAdapter(quotesList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(quotesAdapter);
    }

}