package com.anotherdev.stackapp.util;

import android.util.Log;

import com.anotherdev.stackapp.BuildConfig;
import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.core.CrashlyticsCore;

public final class Logger {

    /* Logging switch */
    private static final boolean LOGGING = BuildConfig.LOGGING;

    /* Enable all level on development.
     * On production depends on isLoggable call
     */
    private static final boolean ON_DEV = BuildConfig.DEBUG;

    private static final boolean ERROR =   ON_DEV || Log.isLoggable("", Log.ERROR);
    private static final boolean WARN =    ON_DEV || Log.isLoggable("", Log.WARN);
    private static final boolean INFO =    ON_DEV || Log.isLoggable("", Log.INFO);
    private static final boolean DEBUG =   ON_DEV || Log.isLoggable("", Log.DEBUG);
    private static final boolean VERBOSE = ON_DEV || Log.isLoggable("", Log.VERBOSE);

    private static final CrashlyticsCore CORE = CrashlyticsCore.getInstance();


    public static void wtf(String tag, String msg) {
        CORE.log(Log.ASSERT, tag, msg);
        //Log.wtf(tag, msg); Crashlytics will output to logcat for you.
    }

    public static void wtf(String tag, String msg, Throwable tr) {
        Log.wtf(tag, msg, tr);
        CORE.logException(tr);
    }

    public static void e(String tag, String msg) {
        if (ERROR) {
            CORE.log(Log.ERROR, tag, msg);
            //Log.e(tag, msg); Crashlytics will output to logcat for you.
        }
    }

    public static void e(String tag, String msg, Throwable tr) {
        if (ERROR) {
            Log.e(tag, msg, tr);
            CORE.logException(tr);
        }
    }

    public static void w(String tag, String msg) {
        if (WARN) {
            CORE.log(Log.WARN, tag, msg);
            //Log.w(tag, msg); Crashlytics will output to logcat for you.
        }
    }

    public static void w(String tag, String msg, Throwable tr) {
        if (WARN) {
            Log.w(tag, msg, tr);
            Crashlytics.logException(tr);
        }
    }

    public static void i(String tag, String msg) {
        if (INFO) {
            CORE.log(Log.INFO, tag, msg);
            //Log.i(tag, msg); Crashlytics will output to logcat for you.
        }
    }

    public static void d(String tag, String msg) {
        if (LOGGING && DEBUG) {
            CORE.log(Log.DEBUG, tag, msg);
            //Log.d(tag, msg); Crashlytics will output to logcat for you.
        }
    }

    public static void v(String tag, String msg) {
        if (LOGGING && VERBOSE) {
            Log.v(tag, msg); // Don't want this level for Crashlytics
        }
    }

    public static int getLoggableLevel() {
        final int level = VERBOSE ? Log.VERBOSE :
                DEBUG   ? Log.DEBUG :
                        INFO    ? Log.INFO :
                                WARN    ? Log.WARN : Log.ERROR;
        return level;
    }

    private Logger() { /* No no objects */ }
}
