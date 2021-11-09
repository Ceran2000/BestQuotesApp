package com.example.bestquotesapp.ui;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.bestquotesapp.R;


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
        viewModel.setOptions("author", author);
    }
}