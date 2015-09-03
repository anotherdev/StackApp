package com.anotherdev.stackapp.api.stackexchange;

import com.google.gson.annotations.SerializedName;

import java.util.Collections;
import java.util.List;

public class Answers {

    @SerializedName("items") List<Answer> answers = Collections.emptyList();


    public List<Answer> get() {
        return answers;
    }
}
