package com.anotherdev.stackapp.rx;

import android.support.annotation.NonNull;

import com.anotherdev.stackapp.util.Logger;

import rx.functions.Action1;

public class Actions {

    public static Action1<Throwable> logError(@NonNull final String tag) {
        return new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Logger.e(tag, "", throwable);
            }
        };
    }


    private Actions() {}
}
