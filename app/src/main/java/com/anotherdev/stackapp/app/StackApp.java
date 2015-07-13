package com.anotherdev.stackapp.app;

import android.app.Application;

import com.anotherdev.stackapp.api.ApiComponent;
import com.anotherdev.stackapp.api.DaggerApiComponent;
import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

public class StackApp extends Application {

    private final ApiComponent mApiComponent = DaggerApiComponent.create();


    @Override
    public void onCreate() {
        super.onCreate();
        initFabric();
    }

    private void initFabric() {
        Fabric.with(this, new Crashlytics());
    }

    public ApiComponent getApiComponent() {
        return mApiComponent;
    }
}
