package com.anotherdev.stackapp.api.stackexchange;

import com.anotherdev.stackapp.util.Htmls;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class Owner {

    String user_id;
    int reputation;
    String profile_image;
    String display_name;
    @SerializedName("link") String profileUrl;


    public Owner() {}

    public String getUserId() {
        return user_id;
    }

    public int getReputation() {
        return reputation;
    }

    public String getProfileImage() {
        return profile_image;
    }

    public CharSequence getDisplayName() {
        return Htmls.fromHtml(display_name);
    }

    public String getProfileUrl() {
        return profileUrl;
    }
}
