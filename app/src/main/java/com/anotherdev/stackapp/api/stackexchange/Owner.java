package com.anotherdev.stackapp.api.stackexchange;

import com.google.gson.annotations.SerializedName;

public class Owner {

    String user_id;
    int reputation;
    String profile_image;
    String display_name;
    @SerializedName("link") String profileUrl;


    public String getUserId() {
        return user_id;
    }

    public int getReputation() {
        return reputation;
    }

    public String getProfileImage() {
        return profile_image;
    }

    public String getDisplayName() {
        return display_name;
    }

    public String getProfileUrl() {
        return profileUrl;
    }
}
