package com.example.ahmed.eyecare.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ahmed on 10/10/17.
 */

public class User {

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("email")
    private String email;
    @SerializedName("code")
    private String code;
    @SerializedName("company")
    private String company;
    @SerializedName("position")
    private String position;
    @SerializedName("bio")
    private String bio;
    @SerializedName("image")
    private String imageUrl;
    @SerializedName("admin")
    private String admin;
    @SerializedName("register_id")
    private String registerID;
    @SerializedName("fb_link")
    private String facebookLink;
    @SerializedName("twitter_link")
    private String twitterLink;
    @SerializedName("linkedin_link")
    private String linkedinLink;
    @SerializedName("event_id")
    private String eventId;
    @SerializedName("location")
    private String location;
    @SerializedName("is_speaker")
    private int isSpeaker;

    public int getId() {
        return id;
    }
}
