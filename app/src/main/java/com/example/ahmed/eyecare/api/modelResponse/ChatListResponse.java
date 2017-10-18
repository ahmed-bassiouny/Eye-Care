package com.example.ahmed.eyecare.api.modelResponse;

import com.example.ahmed.eyecare.model.Chat;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bassiouny on 18/10/17.
 */

public class ChatListResponse extends ParentResponse {

    @SerializedName("chat_list")
    private List<Chat> chatLists;

    public List<Chat> getChatLists() {
        if(chatLists==null)
            chatLists=new ArrayList<>();
        return chatLists;
    }
}
