package com.anotherdev.stackapp.api.stackexchange;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Answers extends RealmObject {

    @PrimaryKey private final String id = Answers.class.getName();
    @SerializedName("items") private RealmList<Answer> answers = new RealmList<>();


    public String getId() {
        return id;
    }

    public void setId(String id) {
    }

    public RealmList<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(RealmList<Answer> a) {
        answers = a;
    }
}
