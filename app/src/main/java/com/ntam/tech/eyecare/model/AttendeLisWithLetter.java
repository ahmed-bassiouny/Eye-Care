package com.ntam.tech.eyecare.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahmed on 15/10/17.
 */

public class AttendeLisWithLetter{

    @SerializedName("letter")
    private String letter;

    @SerializedName("attends")
    private List<Attendee> attendees;

    public List<Attendee> getAttendees() {
        if (attendees == null)
            attendees = new ArrayList<>();
        return attendees;
    }
}
