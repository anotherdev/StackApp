package com.anotherdev.stackapp.api.stackexchange;

import com.anotherdev.stackapp.util.Htmls;

import org.parceler.Parcel;

import java.util.Collections;
import java.util.List;

@Parcel
public class Question extends StackObject {

    String question_id;
    List<String> tags = Collections.emptyList();
    boolean is_answered;
    int view_count;
    int answer_count;
    String title;


    public Question() {}

    public String getId() {
        return question_id;
    }

    public List<String> getTags() {
        return tags;
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

    public CharSequence getTitle() {
        return Htmls.fromHtml(title);
    }
}
