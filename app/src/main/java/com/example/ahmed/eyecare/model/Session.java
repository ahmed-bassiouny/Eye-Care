package com.example.ahmed.eyecare.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahmed on 13/10/17.
 */

public class Session {


    @SerializedName("id")
    private String id;
    @SerializedName("session_name")
    private String sessionName;
    @SerializedName("session_date")
    private String sessionDate;
    @SerializedName("start_time")
    private String startTime;
    @SerializedName("end_time")
    private String endTime;
    @SerializedName("session_tags")
    private String sessionTag;
    @SerializedName("desc")
    private String desc;
    @SerializedName("has_rate")
    private String hasRate;
    @SerializedName("venue")
    private String venue;
    @SerializedName("physical_address")
    private String address;
    @SerializedName("list_of_speaker")
    private List<Speaker> speaker;

    public int getId() {
        try{
        return Integer.parseInt(id);
        }catch (Exception e){
            return 0;
        }
    }

    public String getSessionName() {
        return sessionName;
    }

    public String getSessionDate() {
        return sessionDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getSessionTag() {
        return sessionTag;
    }

    public String getDesc() {
        return desc;
    }

    public boolean getHasRate() {
        if(hasRate.equals("1"))
            return true;
        return false;
    }

    public String getVenue() {
        return venue;
    }

    public String getAddress() {
        return address;
    }

    public List<Speaker> getSpeaker() {
        if(speaker ==null)
            speaker = new ArrayList<>();
        return speaker;
    }
}
