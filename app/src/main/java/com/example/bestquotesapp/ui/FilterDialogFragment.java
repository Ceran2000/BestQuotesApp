package com.example.bestquotesapp.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bestquotesapp.APIClient;
import com.example.bestquotesapp.QuotableAPI;
import com.example.bestquotesapp.R;
import com.example.bestquotesapp.models.Response;

import retrofit2.Call;
import retrofit2.Callback;


public class FilterDialogFragment extends DialogFragment {

    private Button searchByAuthorBtn;
    private EditText etAuthor;
    private QuotesViewModel viewModel;

    public static String TAG = "FilterDialog";

    public FilterDialogFragment() {
        super(R.layout.fragment_filter_dialog);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_filter_dialog, container, false);
        findViews(root);
        setListeners();

        viewModel = new ViewModelProvider(requireActivity()).get(QuotesViewModel.class);

        return root;
    }

    private void findViews(View view){
        searchByAuthorBtn = view.findViewById(R.id.btn_search_by_author);
        etAuthor = view.findViewById(R.id.dialog_et_author);
    }

    private void setListeners(){
        searchByAuthorBtn.setOnClickListener(v -> {
            String author = etAuthor.getText().toString();
            getQuotesByAuthor(author);
            dismiss();
        });
    }

    private void getQuotesByAuthor(String author){
        QuotableAPI quotableAPI = APIClient.getRetrofitInstance().create(QuotableAPI.class);
        Call<Response> call = quotableAPI.getQuotesByAuthor(author);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                viewModel.setQuotes(response.body());
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Toast.makeText(getContext(), "Can't load data...", Toast.LENGTH_LONG).show();
            }
        });
    }

}