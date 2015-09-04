package com.anotherdev.stackapp.api.stackexchange;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import io.realm.QuestionRealmProxy;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

@Parcel(
        implementations = { QuestionRealmProxy.class },
        value = Parcel.Serialization.BEAN,
        analyze = { Question.class }
)
public class Question extends RealmObject {

    @SerializedName("owner") private Owner owner;
    @SerializedName("score") private int score;
    @SerializedName("last_activity_date") private long lastActivityDate;
    @SerializedName("creation_date") private long creationDate;
    @SerializedName("body") private String body;

    @SerializedName("question_id") @PrimaryKey private String questionId;
    @SerializedName("is_answered") private boolean isAnswered;
    @SerializedName("view_count") private int viewCount;
    @SerializedName("answer_count") private int answerCount;
    @SerializedName("title") private String title;


    public Question() {}

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner o) {
        owner = o;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int s) {
        score = s;
    }

    public long getLastActivityDate() {
        return lastActivityDate;
    }

    public void setLastActivityDate(long date) {
        lastActivityDate = date;
    }

    public long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(long date) {
        creationDate = date;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public boolean isAnswered() {
        return isAnswered;
    }

    public void setIsAnswered(boolean answered) {
        isAnswered = answered;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int count) {
        viewCount = count;
    }

    public int getAnswerCount() {
        return answerCount;
    }

    public void setAnswerCount(int count) {
        answerCount = count;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String text) {
        title = text;
    }
}
