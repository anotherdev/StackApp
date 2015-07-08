package com.anotherdev.stackapp.util;

import android.text.format.DateUtils;

import com.crashlytics.android.Crashlytics;
import com.google.common.collect.ImmutableList;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Dates {

    private static final String TAG = Dates.class.getSimpleName();

    private static final String DATE_PATTERN_MICROSECOND = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
    private static final String DATE_PATTERN_SECOND = "yyyy-MM-dd'T'HH:mm:ssZ";

    private static final SimpleDateFormat DATE_FORMAT_MICROSECOND =
            new SimpleDateFormat(DATE_PATTERN_MICROSECOND, Locale.getDefault());
    private static final SimpleDateFormat DATE_FORMAT_SECOND =
            new SimpleDateFormat(DATE_PATTERN_SECOND, Locale.getDefault());

    private static final ImmutableList<SimpleDateFormat> DATE_FORMAT = ImmutableList.of(
            DATE_FORMAT_MICROSECOND,
            DATE_FORMAT_SECOND
    );


    public static CharSequence getTimeAgo(CharSequence text) {
        ParseException exception = null;
        for (DateFormat format : DATE_FORMAT) {
            // Workaround Java bug: http://stackoverflow.com/a/15231574/802421
            String fixed = String.valueOf(text).replaceAll("Z$", "+0000");
            try {
                Date date = format.parse(fixed);
                return getTimeAgo(date.getTime());
            } catch (ParseException e) {
                exception = e;
            }
        }
        Crashlytics.logException(exception);
        return text;
    }


    public static CharSequence getTimeAgo(final long timeInSecond) {
        final long now = System.currentTimeMillis();
        final long timeInMillis = timeInSecond * 1000;
        return DateUtils.getRelativeTimeSpanString(timeInMillis, now, 0);
    }
}
