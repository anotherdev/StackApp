package com.anotherdev.stackapp.api.stackexchange;

import com.anotherdev.stackapp.util.Htmls;

public class StackObject {

    Owner owner;
    int score;
    long last_activity_date;
    long creation_date;
    String body;


    public Owner getOwner() {
        return owner;
    }

    public int getScore() {
        return score;
    }

    public long getLastActivityDate() {
        return last_activity_date;
    }

    public long getCreationDate() {
        return creation_date;
    }

    public CharSequence getBody() {
        return Htmls.fromHtml(body);
    }
}
