package com.example.ahmed.eyecare.utils;

/**
 * Created by ahmed on 11/10/17.
 */

public class MyAccount {

    public static final String TOKEN_KEY ="token";
    public static final String USER_ID_KEY ="userId";
    public static final String USER_IMAGE_KEY ="userImage";

    private String token;
    private int userId;
    private String userImage;

    private MyAccount(Builder builder) {
        token = builder.token;
        userId = builder.userId;
        userImage=builder.userImage;
    }


    public String getToken() {
        return token;
    }

    public String getUserImage() {
        return userImage;
    }

    public int getUserId() {
        return userId;
    }

    public static final class Builder {
        private String token;
        private int userId;
        private String userImage;

        public Builder() {
        }

        public Builder token(String val) {
            token = val;
            return this;
        }

        public Builder userId(int val) {
            userId = val;
            return this;
        }
        public Builder userImage(String val) {
            userImage = val;
            return this;
        }

        public MyAccount build() {
            return new MyAccount(this);
        }
    }
}
