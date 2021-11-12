package com.example.bestquotesapp.ui;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.bestquotesapp.R;

import java.util.ArrayList;
import java.util.HashMap;


public class FilterDialogFragment extends DialogFragment {

    private Button searchByAuthorBtn;
    private EditText etAuthor;
    private QuotesViewModel viewModel;
    private Spinner spinner;
    private RadioGroup radioGroup;
    private HashMap<Integer, String> spinnerMap;
    private String order;

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
        setSpinner();

        viewModel = new ViewModelProvider(requireActivity()).get(QuotesViewModel.class);

        return root;
    }

    private void findViews(View view){
        searchByAuthorBtn = view.findViewById(R.id.btn_search_by_author);
        etAuthor = view.findViewById(R.id.dialog_et_author);
        spinner = view.findViewById(R.id.dialog_spinner_sortBy);
        radioGroup = view.findViewById(R.id.dialog_radio_group);
    }

    private void setListeners(){

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radio_asc:
                        order = "asc";
                        break;
                    case R.id.radio_desc:
                        order = "desc";
                        break;
                }
            }
        });

        searchByAuthorBtn.setOnClickListener(v -> {
            viewModel.clearOptions();
            String author = etAuthor.getText().toString();
            viewModel.addOption("author", author);
            String sortByValue = spinnerMap.get(spinner.getSelectedItemPosition());
            viewModel.addOption("sortBy", sortByValue);
            if (order != null){
                viewModel.addOption("order", order);
            }
            viewModel.fetchQuotesFromServer();
            dismiss();
        });
    }

    private void setSpinner(){
        ArrayList<String> sortingOptions = new ArrayList<>();
        sortingOptions.add("dateAdded");
        sortingOptions.add("dateModified");
        sortingOptions.add("author");
        sortingOptions.add("content");

        ArrayList<String> spinnerValues = new ArrayList<>();
        spinnerValues.add("Date added");
        spinnerValues.add("Date modified");
        spinnerValues.add("Author");
        spinnerValues.add("Content");

        String[] spinnerArray = new String[spinnerValues.size()];
        spinnerMap = new HashMap<>();
        for(int i = 0; i < sortingOptions.size(); i++ ){
            spinnerMap.put(i, sortingOptions.get(i));
            spinnerArray[i] = spinnerValues.get(i);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getContext(), android.R.layout.simple_spinner_item, spinnerArray
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }
}