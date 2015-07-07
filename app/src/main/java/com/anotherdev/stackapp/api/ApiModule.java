package com.anotherdev.stackapp.api;

import com.anotherdev.stackapp.BuildConfig;
import com.anotherdev.stackapp.api.stackexchange.StackOverflowApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;

import static retrofit.RestAdapter.LogLevel;

@Module
public class ApiModule {

    @Provides RestAdapter.Builder provideRestAdapterBuilder() {
        return new RestAdapter.Builder()
                .setLogLevel(BuildConfig.DEBUG ? LogLevel.HEADERS_AND_ARGS : LogLevel.NONE)
                .setErrorHandler(new LoggingErrorHandler());
    }

    @Provides @Singleton StackOverflowApi provideStackOverflowApi(RestAdapter.Builder builder) {
        return builder.setEndpoint(StackOverflowApi.BASE_URL).build().create(StackOverflowApi.class);
    }
}
