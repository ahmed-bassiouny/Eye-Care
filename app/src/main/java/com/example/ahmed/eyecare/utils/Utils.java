package com.example.ahmed.eyecare.utils;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.ahmed.eyecare.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ahmed on 11/10/17.
 */

public class Utils {

    public static void goToFragment(AppCompatActivity appCompatActivity, Fragment fragment, String tag,Bundle bundle) {
        FragmentManager fragmentManager = appCompatActivity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_container, fragment);
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        if (tag != null)
            fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.commit();
    }

    public static Calendar getCalender(String dateTime){
        Date date = null;
        try {
            date = new SimpleDateFormat(Constant.DATE_FORMATE).parse(dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }
}
