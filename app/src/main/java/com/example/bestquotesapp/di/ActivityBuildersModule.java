package com.example.bestquotesapp.di;

import com.example.bestquotesapp.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
            modules = {
                    FragmentBuildersModule.class, ViewModelModule.class
            }
    )
    abstract MainActivity contributeMainActivity();

}
