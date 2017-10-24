package com.ntam.tech.eyecare.api.modelRequest;

/**
 * Created by ahmed on 11/10/17.
 */

public  class ParentRequest {

    public static final String EVENT_KEY = "event_id";
    public static final String USER_ID_KEY = "user_id";
    public static final String PAGE_NUMBER_KEY = "page_number";


    private final static int eventId = 1;

    public static int getEventId(){
        return eventId;
    }
}
