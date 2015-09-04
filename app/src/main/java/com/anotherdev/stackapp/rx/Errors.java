package com.anotherdev.stackapp.rx;

import com.anotherdev.stackapp.util.Logger;

import rx.functions.Func1;

public class Errors {

    private Errors() {}


    public static <T> Func1<Throwable,T> log(final String tag) {
        return log(tag, null);
    }


    public static <T> Func1<Throwable,T> log(final String tag, final T defaultValue) {
        return new Func1<Throwable, T>() {
            @Override
            public T call(Throwable throwable) {
                Logger.e(tag, "", throwable);
                return defaultValue;
            }
        };
    }
}
