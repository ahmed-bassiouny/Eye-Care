package com.example.ahmed.eyecare.model;

import com.example.ahmed.eyecare.utils.Utils;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by ahmed on 13/10/17.
 */

public class Session implements Serializable {


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
    @SerializedName("no_of_interested")
    private String sessioninterested;
    @SerializedName("session_comments")
    private List<Comment> sessionComments;
    @SerializedName("session_likes")
    private String sessionLikes;

    public int getId() {
        try {
            return Integer.parseInt(id);
        } catch (Exception e) {
            return 0;
        }
    }

    public String getSessionName() {
        if (sessionName == null)
            sessionName = "";
        return sessionName;
    }

    public String getSessionDate() {
        return sessionDate;
    }


    private String getSessionTag() {
        return sessionTag;
    }

    public String getDesc() {
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
        return "Main Meeting Room";
    }

    public List<Speaker> getSpeaker() {
        if (speaker == null)
            speaker = new ArrayList<>();
        return speaker;
    }


    public String getFullTimeSession() {
        return Utils.convert24FormatTo12Format(startTime) + " - " + Utils.convert24FormatTo12Format(endTime);
    }

    public String getStartTime() {
        return Utils.convert24FormatTo12Format(startTime);
    }

    public String getEndTime() {
        return Utils.convert24FormatTo12Format(endTime);
    }

    public boolean isMyAgenda() {
        if (addToAgenda.equals("1"))
            return true;
        return false;
    }

    public void setisMyAgenda(boolean agenda) {
        if (agenda) {
            addToAgenda = "1";
        } else {
            addToAgenda = "0";
        }
    }

    public String getSessioninterested() {
        return sessioninterested;
    }

    public int getSessionComments() {
        if (sessionComments == null)
            sessionComments = new ArrayList<>();
        return sessionComments.size();
    }
    public String getTime(){
        return startTime ;
    }
    public String getMonth() {
        return String.valueOf(Utils.getMothStringByNumber(Utils.getCalenderWithThreeDigit(sessionDate).get(Calendar.MONTH)));
    }
    public String getDayDate() {
        return String.valueOf(Utils.getCalenderWithThreeDigit(sessionDate).get(Calendar.DAY_OF_MONTH));
    }

    public String getSessionLikes() {
        return sessionLikes;
    }

}
