package com.example.bestquotesapp.di;

import androidx.lifecycle.ViewModel;

import com.example.bestquotesapp.ui.QuotesViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(QuotesViewModel.class)
    public abstract ViewModel bindQuotesViewModel(QuotesViewModel viewModel);
}
