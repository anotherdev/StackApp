package com.anotherdev.stackapp.intent;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;

import com.anotherdev.stackapp.api.stackexchange.Question;
import com.anotherdev.stackapp.app.DetailActivity;

import org.parceler.Parcels;

public class DetailIntent extends StackIntent {

    private static final String EXTRA_QUESTION = EXTRA + "question";


    public DetailIntent(Context context, Question question) {
        super(context, DetailActivity.class);
        if (question != null) {
            putExtra(EXTRA_QUESTION, Parcels.wrap(question));
        }
    }


    public static Question getQuestion(Intent intent) {
        Parcelable parcel = intent.getParcelableExtra(EXTRA_QUESTION);
        return Parcels.unwrap(parcel);
    }
}
