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
        MyAccount account = new MyAccount(userId,token,userName,userImage,email);
        return account;
    }
    public static void setFullData(Context context,int userId,String userName,String userImage,String email){
        getSharedPref(context);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(MyAccount.USER_ID_KEY,userId);
        editor.putString(MyAccount.USER_NAME_KEY,userName);
        editor.putString(MyAccount.USER_IMAGE_KEY,userImage);
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
