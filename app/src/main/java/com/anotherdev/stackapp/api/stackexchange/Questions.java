package com.anotherdev.stackapp.api.stackexchange;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Questions {

    @SerializedName("items") List<Question> questions;


    public List<Question> get() {
        return questions;
    }
}
