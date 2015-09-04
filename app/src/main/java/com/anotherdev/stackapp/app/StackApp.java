package com.anotherdev.stackapp.app;

import android.app.Application;

import com.anotherdev.stackapp.api.ApiComponent;
import com.anotherdev.stackapp.api.DaggerApiComponent;
import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class StackApp extends Application {

    private final ApiComponent mApiComponent = DaggerApiComponent.create();


    @Override
    public void onCreate() {
        super.onCreate();
        initFabric();
        initRealm();
    }

    private void initFabric() {
        Fabric.with(this, new Crashlytics());
    }

    private void initRealm() {
        RealmConfiguration config = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(config);
    }

    public ApiComponent getApiComponent() {
        return mApiComponent;
    }
}
