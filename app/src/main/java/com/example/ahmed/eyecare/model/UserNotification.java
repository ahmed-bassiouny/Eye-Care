package com.example.ahmed.eyecare.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by bassiouny on 18/10/17.
 */

public class UserNotification {


    @SerializedName("id")
    private String id;
    @SerializedName("title")
    private String title;
    @SerializedName("body")
    private String body;
    @SerializedName("notification_time")
    private String notificationTime;
}
