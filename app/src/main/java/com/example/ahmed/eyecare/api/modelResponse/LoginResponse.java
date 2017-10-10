package com.example.ahmed.eyecare.api.modelResponse;

import com.example.ahmed.eyecare.model.User;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ahmed on 10/10/17.
 */

public class LoginResponse extends ParentResponse {
    @SerializedName("user_data")
    private User user;

    public User getUser() {
        if(user == null)
            user = new User();
        return user;
    }

}
