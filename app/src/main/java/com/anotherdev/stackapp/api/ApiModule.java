package com.anotherdev.stackapp.api;

import com.anotherdev.stackapp.BuildConfig;
import com.anotherdev.stackapp.api.stackexchange.StackOverflowApi;
import com.anotherdev.stackapp.model.RealmExclusionStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

import static retrofit.RestAdapter.LogLevel;

@Module
public class ApiModule {

    @Provides RestAdapter.Builder provideRestAdapterBuilder() {
        Gson gson = new GsonBuilder()
                .setExclusionStrategies(new RealmExclusionStrategy())
                .create();

        return new RestAdapter.Builder()
                .setConverter(new GsonConverter(gson))
                .setLogLevel(BuildConfig.DEBUG ? LogLevel.HEADERS_AND_ARGS : LogLevel.NONE)
                .setErrorHandler(new LoggingErrorHandler());
    }

    @Provides @Singleton StackOverflowApi provideStackOverflowApi(RestAdapter.Builder builder) {
        return builder.setEndpoint(StackOverflowApi.BASE_URL).build().create(StackOverflowApi.class);
    }
}
