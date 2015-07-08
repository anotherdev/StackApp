package com.anotherdev.stackapp.util;

import android.text.Html;
import android.text.TextUtils;

public class Htmls {

    public static CharSequence trimTrailingWhitespace(CharSequence source) {
        if(TextUtils.isEmpty(source)) {
            return source;
        }

        int len = source.length();
        // loop back to the first non-whitespace character
        while(--len >= 0 && Character.isWhitespace(source.charAt(len)));
        return source.subSequence(0, len+1);
    }

    public static CharSequence fromHtml(String source) {
        return trimTrailingWhitespace(Html.fromHtml(source));
    }
}
