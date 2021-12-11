package com.example.bestquotesapp.ui;

import android.app.AlertDialog;

import android.os.Bundle;


import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.text.InputType;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.bestquotesapp.R;
import com.example.bestquotesapp.models.Quote;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;


public class QuotesFragment extends DaggerFragment {

    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private LinearLayout pagesListLayout;

    @Inject
    ViewModelFactory viewModelFactory;

    private QuotesViewModel viewModel;


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

        viewModel = new ViewModelProvider(requireActivity(), viewModelFactory).get(QuotesViewModel.class);
        viewModel.getQuotes().observe(getViewLifecycleOwner(), quotes -> {
            if (quotes != null) {
                if (quotes.getCount() != 0) {
                    generateQuotesList(quotes.getResults());
                    generatePages();
                }
            }
        });

        return root;
    }

    private void findViews(View root) {
        recyclerView = root.findViewById(R.id.recyclerViewQuotes);
        fab = root.findViewById(R.id.fab);
        pagesListLayout = root.findViewById(R.id.pagesListLayout);
    }

    private void setListeners() {
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

    private void generatePages() {
        pagesListLayout.removeAllViews();
        int totalPages = Objects.requireNonNull(viewModel.getQuotes().getValue()).getTotalPages();

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);

        int i = 1;
        if (totalPages > 1 && totalPages <= 5) {
            params.weight = 1.0f / totalPages;
            while (i <= totalPages) {
                Button button = setPageButton(String.valueOf(i));
                button.setLayoutParams(params);
                button.setOnClickListener(v -> {
                    viewModel.addOption("page", v.getTag().toString());
                    viewModel.fetchQuotesFromServer();
                });
                pagesListLayout.addView(button);
                i++;
            }
        } else if (totalPages > 5) {
            params.weight = 1.0f / 7.0f;
            while (i <= totalPages) {
                Button button = setPageButton(String.valueOf(i));
                button.setLayoutParams(params);
                button.setOnClickListener(v -> {
                    viewModel.addOption("page", v.getTag().toString());
                    viewModel.fetchQuotesFromServer();
                });
                pagesListLayout.addView(button);
                if (i == 5) i = totalPages;
                else i++;
            }
            Button button = setPageButton("...");
            button.setLayoutParams(params);
            button.setOnClickListener(v -> {

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Set page number");


                final EditText input = new EditText(getContext());
                input.setInputType(InputType.TYPE_CLASS_NUMBER);
                builder.setView(input);

                builder.setPositiveButton("OK", (dialog, which) -> {
                    if (Integer.parseInt(input.getText().toString()) > totalPages) {
                        Toast.makeText(getContext(), "The max amount is " + totalPages, Toast.LENGTH_LONG).show();
                    } else {
                        viewModel.addOption("page", input.getText().toString());
                        viewModel.fetchQuotesFromServer();
                    }
                });

                builder.setNegativeButton("Cancel", ((dialog, which) -> dialog.cancel()));

                builder.show();

            });
            pagesListLayout.addView(button);
        }
    }

    private Button setPageButton(String page) {
        Button button = new Button(getContext());
        button.setTag(page);
        button.setText(page);
        button.setWidth(0);
        return button;
    }

    @Override
    public void onDestroy() {
        viewModel.unSubscribe();
        super.onDestroy();
    }
}