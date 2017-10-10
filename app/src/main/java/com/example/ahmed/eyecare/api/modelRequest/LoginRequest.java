package com.example.ahmed.eyecare.api.modelRequest;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ahmed on 10/10/17.
 */

public class LoginRequest {
    @SerializedName("register_id")
    private String registerId; // token id
    @SerializedName("event_id")
    private int eventId = 1; // fixed
    @SerializedName("email")
    private String email;
    @SerializedName("code")
    private String code;

    private LoginRequest(Builder builder) {
        email = builder.email;
        code = builder.code;
    }


    public static final class Builder {
        private String email;
        private String code;

        public Builder() {
        }

        public Builder email(String val) {
            email = val;
            return this;
        }

        public Builder code(String val) {
            code = val;
            return this;
        }

        public LoginRequest build() {
            return new LoginRequest(this);
        }
    }
}
