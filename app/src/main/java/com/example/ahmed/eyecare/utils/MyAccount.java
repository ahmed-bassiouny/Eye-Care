package com.example.ahmed.eyecare.utils;

/**
 * Created by ahmed on 11/10/17.
 */

public class MyAccount {

    public static final String TOKEN_KEY ="token";
    private String token;

    private MyAccount(Builder builder) {
        token = builder.token;
    }


    public String getToken() {
        return token;
    }

    public static final class Builder {
        private String token;

        public Builder() {
        }

        public Builder token(String val) {
            token = val;
            return this;
        }

        public MyAccount build() {
            return new MyAccount(this);
        }
    }
}
