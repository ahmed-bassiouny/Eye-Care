package com.example.ahmed.eyecare.utils;

/**
 * Created by ahmed on 11/10/17.
 */

public class MyAccount {

    public static final String TOKEN_KEY ="token";
    public static final String USER_ID_KEY ="userId";
    public static final String USER_NAME_KEY ="userName";
    public static final String USER_IMAGE_KEY ="userImage";
    public static final String EMAIL_KEY ="email";


    private String token;
    private int userId;
    private String userName;
    private String userImage;
    private String email;

    public MyAccount(int userId,String token,String userName,String userImage,String email){
        this.userId=userId;
        this.email=email;
        this.userName=userName;
        this.userImage=userImage;
        this.token=token;
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

    public String getEmail() {
        return email;
    }

    public String getUserName() {
        return userName;
    }
}
