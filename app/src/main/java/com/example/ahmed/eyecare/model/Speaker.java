package com.example.ahmed.eyecare.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahmed on 13/10/17.
 */

public class Speaker implements Serializable {

    //        "sessions"
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("bio")
    private String bio;
    @SerializedName("image")
    private String image;
    @SerializedName("position")
    private String position;
    @SerializedName("company")
    private String company;
    @SerializedName("sessions")
    private List<Session> session;

    public int getId() {
        try {
            return Integer.parseInt(id);
        } catch (Exception e) {
            return 0;
        }
    }

    public String getName() {
        return name;
    }

    public String getBio() {
        if (bio == null)
            bio = "";
        return bio;
    }

    public String getImage() {
        return image;
    }

    public String getPosition() {
        return position;
    }

    public String getCompany() {
        if (company == null)
            company = "";
        return company;
    }

    public List<Session> getSession() {
        if (session == null)
            session = new ArrayList<>();
        return session;
    }

    public void setSessions(List<Session> session) {
        if (session != null)
            this.session = session;
    }
}
