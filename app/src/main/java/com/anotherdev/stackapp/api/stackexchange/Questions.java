package com.anotherdev.stackapp.api.stackexchange;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Questions extends RealmObject {

    @SerializedName("items") private RealmList<Question> questions = new RealmList<>();


    public RealmList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(RealmList<Question> q) {
        questions = q;
    }
}
