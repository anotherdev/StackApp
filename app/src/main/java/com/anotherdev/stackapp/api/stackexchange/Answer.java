package com.anotherdev.stackapp.api.stackexchange;

public class Answer extends StackObject {

    String answer_id;
    boolean is_accepted;
    String question_id;


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
