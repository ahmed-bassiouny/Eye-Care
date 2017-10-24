package com.ntam.tech.eyecare.api.modelResponse;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ahmed on 10/10/17.
 */

public class ParentResponse {

    public static final String trueResult = "true";

    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message="";
    @SerializedName("massage")
    private String massage="";

    public boolean getStatus() {
        if (status.equals(trueResult))
            return true;
        return false;
    }

    public String getMassage() {
        if (message !=null &&!message.isEmpty())
            return message;
        else if (massage !=null &&!massage.isEmpty())
            return massage;
        else
            return "Sorry we can\'t load data";
    }
}
