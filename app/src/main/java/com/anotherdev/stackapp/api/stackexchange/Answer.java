package com.anotherdev.stackapp.api.stackexchange;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import io.realm.AnswerRealmProxy;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

@Parcel(
        implementations = { AnswerRealmProxy.class },
        value = Parcel.Serialization.BEAN,
        analyze = { Answer.class }
)
public class Answer extends RealmObject {

    @SerializedName("owner") private Owner owner;
    @SerializedName("score") private int score;
    @SerializedName("last_activity_date") private long lastActivityDate;
    @SerializedName("creation_date") private long creationDate;
    @SerializedName("body") private String body;

    @SerializedName("answer_id") @PrimaryKey private String answerId;
    @SerializedName("is_accepted") private boolean isAccepted;
    @SerializedName("question_id") private String questionId;


    public Answer() {}

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

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String id) {
        answerId = id;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setIsAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String id) {
        questionId = id;
    }
}
