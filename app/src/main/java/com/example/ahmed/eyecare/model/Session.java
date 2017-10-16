package com.example.ahmed.eyecare.model;

import com.example.ahmed.eyecare.utils.Utils;
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
    private String location;
    @SerializedName("list_of_speaker")
    private List<Speaker> speaker;
    @SerializedName("add_to_agenda")
    private String addToAgenda;

    public int getId() {
        try {
            return Integer.parseInt(id);
        } catch (Exception e) {
            return 0;
        }
    }

    public String getSessionName() {
        return sessionName;
    }

    public String getSessionDate() {
        return sessionDate;
    }

    private String getStartTime() {
        return startTime;
    }

    private String getEndTime() {
        return endTime;
    }

    private String getSessionTag() {
        return sessionTag;
    }

    private String getDesc() {
        return desc;
    }

    private boolean getHasRate() {
        if (hasRate.equals("1"))
            return true;
        return false;
    }

    private String getVenue() {
        return venue;
    }

    public String getLocation() {
        if (location == null || location.isEmpty())
            location = "No Location";
        return location;
    }

    private List<Speaker> getSpeaker() {
        if (speaker == null)
            speaker = new ArrayList<>();
        return speaker;
    }

    public String getspeakerCount() {
        return String.valueOf(speaker.size());
    }

    public String getFullTimeSession() {
        return Utils.convert24FormatTo12Format(startTime) + " - " + Utils.convert24FormatTo12Format(endTime);
    }

    public boolean getAddToAgenda() {
        if (addToAgenda.equals("1"))
            return true;
        return false;
    }
}
