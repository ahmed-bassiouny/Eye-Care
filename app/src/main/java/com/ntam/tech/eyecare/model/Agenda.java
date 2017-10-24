package com.ntam.tech.eyecare.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bassiouny on 16/10/17.
 */

public class Agenda {

    @SerializedName("day_number")
    private String dayNumber;
    @SerializedName("event_date")
    private String eventDate;
    @SerializedName("sessions")
    private List<Session> sessions;

    public String getDayNumber() {
        if(dayNumber==null)
            dayNumber="";
        return dayNumber;
    }

    public String getEventDate() {
        if(eventDate==null)
            eventDate="";
        return eventDate;
    }

    public List<Session> getSessions() {
        if (sessions==null)
            sessions= new ArrayList<>();
        return sessions;
    }

    public void setDayNumber(String dayNumber) {
        this.dayNumber = dayNumber;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }
}
