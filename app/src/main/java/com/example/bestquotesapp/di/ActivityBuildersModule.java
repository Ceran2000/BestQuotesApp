package com.example.bestquotesapp.di;

import android.util.Log;

import com.example.bestquotesapp.MainActivity;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector
    abstract MainActivity contributeMainActivity();

}
