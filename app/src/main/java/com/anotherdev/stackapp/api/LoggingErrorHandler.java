package com.anotherdev.stackapp.api;

import com.anotherdev.stackapp.util.Logger;

import retrofit.ErrorHandler;
import retrofit.RetrofitError;

public class LoggingErrorHandler implements ErrorHandler {

    private static final String TAG = LoggingErrorHandler.class.getSimpleName();


    @Override
    public Throwable handleError(RetrofitError cause) {
        Logger.e(TAG, "", cause);
        return cause;
    }
}
