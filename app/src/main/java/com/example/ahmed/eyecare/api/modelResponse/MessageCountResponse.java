package com.example.ahmed.eyecare.api.modelResponse;

import com.google.gson.annotations.SerializedName;

/**
 * Created by bassiouny on 18/10/17.
 */

public class MessageCountResponse extends ParentResponse {

    @SerializedName("total_message")
    private int totalMessage;

    public int getTotalMessage() {
        return totalMessage;
    }
}
