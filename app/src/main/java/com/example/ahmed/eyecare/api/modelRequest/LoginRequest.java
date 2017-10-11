package com.example.ahmed.eyecare.api.modelRequest;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ahmed on 10/10/17.
 */

public class LoginRequest {

    public static final String TOKEN_KEY = "register_id";
    public static final String EVENT_KEY = "event_id";
    public static final String EMAIL_KEY = "email";
    public static final String CODE_KEY = "code";

    private int eventId = 1; // fixed

    public int getEventId(){
        return eventId;
    }
}
