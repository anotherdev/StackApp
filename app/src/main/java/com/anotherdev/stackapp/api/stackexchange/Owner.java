package com.anotherdev.stackapp.api.stackexchange;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import io.realm.OwnerRealmProxy;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

@Parcel(
        implementations = { OwnerRealmProxy.class },
        value = Parcel.Serialization.BEAN,
        analyze = { Owner.class }
)
public class Owner extends RealmObject {

    @SerializedName("user_id") @PrimaryKey private String userId = "";
    @SerializedName("reputation") private int reputation;
    @SerializedName("profile_image") private String profileImage;
    @SerializedName("display_name") private String displayName;
    @SerializedName("link") private String profileUrl;


    public Owner() {}

    public String getUserId() {
        return userId;
    }

    public void setUserId(String id) {
        userId = id;
    }

    public int getReputation() {
        return reputation;
    }

    public void setReputation(int rep) {
        reputation = rep;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String imageUrl) {
        profileImage = imageUrl;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String name) {
        displayName = name;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String url) {
        profileUrl = url;
    }
}
