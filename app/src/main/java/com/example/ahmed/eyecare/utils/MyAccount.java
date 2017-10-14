package com.example.ahmed.eyecare.utils;

/**
 * Created by ahmed on 11/10/17.
 */

public class MyAccount {

    public static final String TOKEN_KEY ="token";
    public static final String USER_ID_KEY ="userId";
    private String token;
    private int userId;

    private MyAccount(Builder builder) {
        token = builder.token;
        userId = builder.userId;
    }


    public String getToken() {
        return token;
    }

    public int getUserId() {
        return userId;
    }

    public static final class Builder {
        private String token;
        private int userId;

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

        public MyAccount build() {
            return new MyAccount(this);
        }
    }
}
