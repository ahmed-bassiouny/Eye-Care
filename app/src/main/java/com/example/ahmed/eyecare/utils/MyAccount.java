package com.example.ahmed.eyecare.utils;

/**
 * Created by ahmed on 11/10/17.
 */

public class MyAccount {
/*

        "code": "cyprus2017",
        "company": "Ntam Tech",
        "position": "Team Leader",
        "bio": "Ntam Tech Admin Bio",
        "admin": "1",
        ,
        "event_id": "1",
        "location": "",
        "is_speaker": 0*/
    public static final String TOKEN_KEY ="token";
    public static final String USER_ID_KEY ="userId";
    public static final String USER_NAME_KEY ="userName";
    public static final String USER_IMAGE_KEY ="userImage";
    public static final String EMAIL_KEY ="email";

    public static final String ADMIN_KEY = "admin";
    public static final String COUNTRY_KEY = "country";
    public static final String MOBILE_KEY = "mobile";
    public static final String HOSPITAL_KEY = "hospital";


    private String token;
    private int userId;
    private String userName;
    private String userImage;
    private String email;
    private boolean admin;

    public MyAccount(int userId,String token,String userName,String userImage,String email,boolean admin){
        this.userId=userId;
        this.email=email;
        this.userName=userName;
        this.userImage=userImage;
        this.token=token;
        this.admin=admin;
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
}
