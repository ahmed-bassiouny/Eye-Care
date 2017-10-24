package com.ntam.tech.eyecare.utils;

/**
 * Created by ahmed on 11/10/17.
 */

public class MyAccount {

    public static final String TOKEN_KEY ="token";
    public static final String USER_ID_KEY ="userId";
    public static final String USER_NAME_KEY ="userName";
    public static final String USER_IMAGE_KEY ="userImage";
    public static final String EMAIL_KEY ="email";
    public static final String POSITION_KEY ="position";

    public static final String ADMIN_KEY = "admin";
    public static final String BIO_KEY = "bio";
    public static final String COUNTRY_KEY = "country";
    public static final String MOBILE_KEY = "mobile";
    public static final String HOSPITAL_KEY = "hospital";


    private String token;
    private int userId;
    private String userName;
    private String userImage;
    private String email;
    private boolean admin;
    private String bio;
    private String position;

    public MyAccount(int userId,String token,String userName,String userImage,String email,boolean admin,String bio,String position){
        this.userId=userId;
        this.email=email;
        this.userName=userName;
        this.userImage=userImage;
        this.token=token;
        this.admin=admin;
        this.bio=bio;
        this.position=position;
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

    public boolean isAdmin(){
        return admin;
    }

    public String getBio() {
        return bio;
    }

    public String getPosition() {
        return position;
    }
}
