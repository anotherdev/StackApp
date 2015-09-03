package com.anotherdev.stackapp.api.stackexchange;

import com.google.gson.annotations.SerializedName;

import java.util.Collections;
import java.util.List;

public class Questions {

    @SerializedName("items") List<Question> questions = Collections.emptyList();


    public List<Question> get() {
        return questions;
    }
}
