package com.ntam.tech.eyecare.api.modelResponse;

import com.ntam.tech.eyecare.model.AttendeLisWithLetter;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahmed on 15/10/17.
 */

public class AttendeeListResponse extends ParentResponse {
    @SerializedName("all_attends")
    private List<AttendeLisWithLetter> attendees;

    public List<AttendeLisWithLetter> getAttendeesWithLetter() {
        if (attendees == null)
            attendees = new ArrayList<>();
        return attendees;
    }
}
