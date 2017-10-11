package com.example.ahmed.eyecare.api.modelRequest;

/**
 * Created by ahmed on 11/10/17.
 */

public  class ParentRequest {

    public static final String EVENT_KEY = "event_id";

    static private int eventId = 1; // fixed

    public static int getEventId(){
        return eventId;
    }
}
