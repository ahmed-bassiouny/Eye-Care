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
        MyAccount account = new MyAccount.Builder().token(token).build();
        return account;
    }
    public static void setToken(Context context,String token){
        getSharedPref(context);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(MyAccount.TOKEN_KEY, token);
        editor.commit();
    }

}
