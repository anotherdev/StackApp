package com.anotherdev.stackapp.app;

import android.app.Application;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

public class StackApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initFabric();
    }

    private void initFabric() {
        Fabric.with(this, new Crashlytics());
    }
}
