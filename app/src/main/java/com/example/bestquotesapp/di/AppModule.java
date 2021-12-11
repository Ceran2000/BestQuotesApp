package com.example.bestquotesapp.di;

import static com.example.bestquotesapp.util.Constants.BASE_URL;

import com.example.bestquotesapp.QuotableAPI;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


@Module(includes = ViewModelModule.class)
public class AppModule {

    @Singleton
    @Provides
    static Retrofit provideRetrofitInstance(){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    static QuotableAPI provideQuotableApi(Retrofit retrofit){
        return retrofit.create(QuotableAPI.class);
    }
}
