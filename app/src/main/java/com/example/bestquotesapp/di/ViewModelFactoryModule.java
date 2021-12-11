package com.example.bestquotesapp.di;

import androidx.lifecycle.ViewModelProvider;

import com.example.bestquotesapp.ui.ViewModelFactory;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ViewModelFactoryModule {

    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory viewModelFactory);
}
