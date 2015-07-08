package com.anotherdev.stackapp.intent;

import android.content.Context;

import com.anotherdev.stackapp.app.DetailActivity;

public class DetailIntent extends StackIntent {

    public DetailIntent(Context context) {
        super(context, DetailActivity.class);
    }
}
