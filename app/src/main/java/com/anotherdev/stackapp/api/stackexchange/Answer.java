package com.anotherdev.stackapp.api.stackexchange;

import com.anotherdev.stackapp.util.Htmls;

public class Answer {

    Owner owner;
    int score;
    long last_activity_date;
    long creation_date;
    String body;

    String answer_id;
    boolean is_accepted;
    String question_id;


    public Answer() {}

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

    public String getId() {
        return answer_id;
    }

    public boolean isAccepted() {
        return is_accepted;
    }

    public String getQuestionId() {
        return question_id;
    }
}
