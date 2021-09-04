package com.example.bestquotesapp.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bestquotesapp.R;


public class FilterDialogFragment extends DialogFragment {

    public static String TAG = "FilterDialog";

    public FilterDialogFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_filter_dialog, container, false);
        return root;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = new AlertDialog.Builder(requireContext())
                .setMessage("Test Message")
                .setPositiveButton("OK", (x, _x) -> getDialog().cancel() )
                .create();
        return dialog;
    }
}