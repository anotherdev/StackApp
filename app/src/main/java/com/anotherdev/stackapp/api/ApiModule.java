package com.anotherdev.stackapp.api;

import com.anotherdev.stackapp.BuildConfig;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;

import static retrofit.RestAdapter.LogLevel;

@Module
public class ApiModule {

    protected RestAdapter.Builder createRestAdapterBuilder(String endpoint) {
        return new RestAdapter.Builder()
                .setEndpoint(endpoint)
                .setLogLevel(BuildConfig.DEBUG ? LogLevel.HEADERS_AND_ARGS : LogLevel.NONE)
                .setErrorHandler(new LoggingErrorHandler());
    }

    @Provides @Singleton StackOverflowApi provideStackOverflowApi() {
        return createRestAdapterBuilder(StackOverflowApi.BASE_URL).build().create(StackOverflowApi.class);
    }
}
