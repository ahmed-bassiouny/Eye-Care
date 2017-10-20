package com.example.ahmed.eyecare.api.modelRequest;

/**
 * Created by ahmed on 20/10/17.
 */

public class AddNotificationRequest {
    public static final String TITLE = "title";
    public static final String BODY = "body";
    public static final String ADD_TO_DRAFT = "add_draft";
    public static final String TARGET ="notification_target";


    private final static int draft = 0;

    public static int addToDraft(){
        return draft;
    }
}
