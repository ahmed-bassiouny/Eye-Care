package com.example.ahmed.eyecare.api.modelResponse;

import com.example.ahmed.eyecare.R;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ahmed on 10/10/17.
 */

public class ParentResponse {

    public static final String trueResult= "true";

    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message;

    public boolean getStatus() {
        if (status.equals(trueResult))
            return true;
        return false;
    }

    public String getMassage() {
        if(message==null || message.isEmpty())
            message= "Sorry we can\'t load data";
        return message;
    }
}
