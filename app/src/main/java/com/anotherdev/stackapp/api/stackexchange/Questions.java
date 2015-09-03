package com.anotherdev.stackapp.api.stackexchange;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Questions extends RealmObject {

    @PrimaryKey private final String id = "questions";
    @SerializedName("items") private RealmList<Question> questions = new RealmList<>();


    public String getId() {
        return id;
    }

    public void setId(String id) {
    }

    public RealmList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(RealmList<Question> q) {
        questions = q;
    }
}
