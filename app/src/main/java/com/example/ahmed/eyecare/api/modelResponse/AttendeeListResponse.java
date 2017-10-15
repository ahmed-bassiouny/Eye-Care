package com.example.ahmed.eyecare.api.modelResponse;

import com.example.ahmed.eyecare.model.AttendeLisWithLetter;
import com.example.ahmed.eyecare.model.Attendee;
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
