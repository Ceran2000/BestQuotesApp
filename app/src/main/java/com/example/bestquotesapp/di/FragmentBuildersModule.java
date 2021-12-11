package com.example.bestquotesapp.di;

import com.example.bestquotesapp.ui.FilterDialogFragment;
import com.example.bestquotesapp.ui.QuotesFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract QuotesFragment contributeQuotesFragment();

    @ContributesAndroidInjector
    abstract FilterDialogFragment contributeFilterDialogFragment();
}
