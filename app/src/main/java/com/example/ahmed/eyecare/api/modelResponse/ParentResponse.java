package com.example.ahmed.eyecare.api.modelResponse;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ahmed on 10/10/17.
 */

public abstract class ParentResponse {

    public static final String trueResult= "true";

    @SerializedName("status")
    private String status;
    @SerializedName("massage")
    private String massage;

    public boolean getStatus() {
        if (status.equals(trueResult))
            return true;
        return false;
    }

    public String getMassage() {
        return massage;
    }
}
