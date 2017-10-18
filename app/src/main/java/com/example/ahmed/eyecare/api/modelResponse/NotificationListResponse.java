package com.example.ahmed.eyecare.api.modelResponse;

import com.example.ahmed.eyecare.model.UserNotification;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bassiouny on 18/10/17.
 */

public class NotificationListResponse extends ParentResponse {

    @SerializedName("sent")
    private List<UserNotification> userNotificationList;

    public List<UserNotification> getUserNotificationList() {
        if(userNotificationList==null)
            userNotificationList= new ArrayList<>();
        return userNotificationList;
    }
}
