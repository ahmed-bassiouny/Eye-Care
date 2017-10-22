package com.example.ahmed.eyecare.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ahmed on 11/10/17.
 */

public class SharedPref {

    private final static String sharedPrefName = "EyeCare";


    private static SharedPreferences sharedPref ;

    private static void getSharedPref(Context context){
        if(sharedPref == null)
            sharedPref = context.getSharedPreferences(sharedPrefName, Context.MODE_PRIVATE);
    }
    public static MyAccount getMyAccount(Context context){
        getSharedPref(context);
        String token = sharedPref.getString(MyAccount.TOKEN_KEY,"");
        int userId = sharedPref.getInt(MyAccount.USER_ID_KEY,0);
        String userName = sharedPref.getString(MyAccount.USER_NAME_KEY,"");
        String userImage= sharedPref.getString(MyAccount.USER_IMAGE_KEY,"");
        String email = sharedPref.getString(MyAccount.EMAIL_KEY,"");
        boolean admin = sharedPref.getBoolean(MyAccount.ADMIN_KEY,false);
        MyAccount account = new MyAccount(userId,token,userName,userImage,email,admin);
        return account;
    }
    public static void setMainInfo(Context context, int userId, String userName, String userImage, String email, boolean admin){
        getSharedPref(context);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(MyAccount.USER_ID_KEY,userId);
        editor.putString(MyAccount.USER_NAME_KEY,userName);
        editor.putString(MyAccount.USER_IMAGE_KEY,userImage);
        editor.putString(MyAccount.EMAIL_KEY,email);
        editor.putBoolean(MyAccount.ADMIN_KEY,admin);
        editor.commit();
    }
    public static void setSubInfo(Context context, String country, String hospital, String mobile,String email){
        getSharedPref(context);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(MyAccount.COUNTRY_KEY,country);
        editor.putString(MyAccount.HOSPITAL_KEY,hospital);
        editor.putString(MyAccount.MOBILE_KEY,mobile);
        editor.putString(MyAccount.EMAIL_KEY,email);
        editor.commit();
    }

    public static void setToken(Context context,String token){
        getSharedPref(context);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(MyAccount.TOKEN_KEY, token);
        editor.commit();
    }

}
