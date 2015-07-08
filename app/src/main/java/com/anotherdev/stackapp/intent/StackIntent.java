package com.anotherdev.stackapp.intent;

import android.content.Context;
import android.content.Intent;

import com.anotherdev.stackapp.BuildConfig;

public abstract class StackIntent extends Intent {

    public static final String ACTION = BuildConfig.APPLICATION_ID + ".intent.action.";
    public static final String EXTRA = BuildConfig.APPLICATION_ID + ".intent.extra.";


    StackIntent(String action) {
        super(action);
    }

    StackIntent(Context packageContext, Class<?> cls) {
        super(packageContext, cls);
    }
}
