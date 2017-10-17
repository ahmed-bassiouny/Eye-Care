package com.example.ahmed.eyecare.model;

import com.example.ahmed.eyecare.utils.Utils;
import com.google.gson.annotations.SerializedName;

import java.util.Calendar;

/**
 * Created by bassiouny on 17/10/17.
 */

public class Message {

    @SerializedName("id")
    private String id;
    @SerializedName("message")
    private String message;
    @SerializedName("message_time")
    private String messageTime;
    @SerializedName("if_owner")
    private int myMessage;

    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    private String getMessageTime() {
        return messageTime;
    }

    public boolean isMyMessage() {
        if(myMessage==1)
            return true;
        return false;
    }
    public String getTime(){
        return String.valueOf(Utils.getCalender(messageTime).get(Calendar.HOUR)+":"+Utils.getCalender(messageTime).get(Calendar.MINUTE));
    }
}
