package com.anotherdev.stackapp.model;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

import io.realm.RealmObject;

public class RealmExclusionStrategy implements ExclusionStrategy {

    @Override
    public boolean shouldSkipClass(Class<?> clazz) {
        return false;
    }

    @Override
    public boolean shouldSkipField(FieldAttributes f) {
        return f.getDeclaringClass().equals(RealmObject.class);
    }
}
