package com.example.ahmed.eyecare.api.modelResponse;

import com.example.ahmed.eyecare.model.Message;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bassiouny on 17/10/17.
 */

public class MessageDetailsResponse extends ParentResponse {

    @SerializedName("chat_messages")
    private List<Message> messageList;

    public List<Message> getMessageList() {
        if(messageList==null)
            messageList= new ArrayList<>();
        return messageList;
    }
}
