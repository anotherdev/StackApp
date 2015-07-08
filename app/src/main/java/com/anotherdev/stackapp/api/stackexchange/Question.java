package com.anotherdev.stackapp.api.stackexchange;

import com.anotherdev.stackapp.util.Htmls;

import java.util.Collections;
import java.util.List;

public class Question {

    String question_id;
    List<String> tags = Collections.emptyList();
    Owner owner;
    boolean is_answered;
    int view_count;
    int answer_count;
    int score;
    long last_activity_date;
    long creation_date;
    String title;
    String body;


    public String getId() {
        return question_id;
    }

    public List<String> getTags() {
        return tags;
    }

    public Owner getOwner() {
        return owner;
    }

    public boolean isAnswered() {
        return is_answered;
    }

    public int getViewCount() {
        return view_count;
    }

    public int getAnswerCount() {
        return answer_count;
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

    public CharSequence getTitle() {
        return Htmls.fromHtml(title);
    }

    public CharSequence getBody() {
        return Htmls.fromHtml(body);
    }
}
