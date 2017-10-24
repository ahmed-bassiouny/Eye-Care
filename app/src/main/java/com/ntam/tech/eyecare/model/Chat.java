package com.ntam.tech.eyecare.model;

import com.ntam.tech.eyecare.utils.Utils;
import com.google.gson.annotations.SerializedName;

import java.util.Calendar;

/**
 * Created by bassiouny on 18/10/17.
 */

public class Chat {

    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("image")
    private String image;
    @SerializedName("message")
    private String message;
    @SerializedName("message_time")
    private String messageTime;
    @SerializedName("unread_meassage")
    private String unread;

    public int getId() {
        try {
            return Integer.parseInt(id);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getMessage() {
        return message;
    }

    private String getMessageTime() {
        return messageTime;
    }
    public String getTime(){
        return String.valueOf(Utils.getCalender(messageTime).get(Calendar.HOUR)+":"+Utils.getCalender(messageTime).get(Calendar.MINUTE));
    }
    public boolean isUnread(){
        if(unread.equals("1"))
            return true;
        return false;
    }
}
