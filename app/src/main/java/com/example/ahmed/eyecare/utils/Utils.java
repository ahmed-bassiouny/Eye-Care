package com.example.ahmed.eyecare.utils;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.ahmed.eyecare.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ahmed on 11/10/17.
 */

public class Utils {

    public static void goToFragment(FragmentActivity fragmentActivity, Fragment fragment, String tag, Bundle bundle) {
        FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_container, fragment);
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        if (tag != null)
            fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.commit();
    }

    public static Calendar getCalender(String dateTime) {
        Date date = null;
        try {
            date = new SimpleDateFormat(Constant.DATE_FORMAT).parse(dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    public static void setImage(final Context context, String imgUrl, CircleImageView circleImageView) {
        ImageLoader imageLoader = ImageLoader.getInstance();

        DisplayImageOptions.Builder options = new DisplayImageOptions.Builder()
                .resetViewBeforeLoading(true)
                .cacheInMemory(false)
                .considerExifParams(true)
                .cacheOnDisk(true)
                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2);
        options.showImageOnLoading(R.drawable.placeholderperson);
        options.showImageForEmptyUri(R.drawable.placeholderperson);
        options.showImageOnFail(R.drawable.placeholderperson);
        imageLoader.init(ImageLoaderConfiguration.createDefault(context));
        imageLoader.displayImage(imgUrl, circleImageView, options.build());
    }

    public static String getMothStringByNumber(int number) {
        String result = "";
        // TODO check if language arabic or english to return value
        switch (number) {
            case 0:
                result = "January";
                break;
            case 1:
                result = "February";
                break;
            case 2:
                result = "March";
                break;
            case 3:
                result = "April";
                break;
            case 4:
                result = "May";
                break;
            case 5:
                result = "June";
                break;
            case 6:
                result = "July";
                break;
            case 7:
                result = "August";
                break;
            case 8:
                result = "September ";
                break;
            case 9:
                result = "October";
                break;
            case 10:
                result = "November";
                break;
            case 11:
                result = "December";
                break;
        }
        return result;
    }

    public static String convert24FormatTo12Format(String originalTime) {
        DateFormat originalFormat = new SimpleDateFormat(Constant.DATE_FULL_TIME_FORMAT, Locale.ENGLISH);
        DateFormat targetFormat = new SimpleDateFormat(Constant.DATE_TIME_FORMAT);
        Date date = null;
        try {
            date = originalFormat.parse(originalTime);
            return targetFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static SimpleDateFormat getSimpleDateFormate() {
        return new SimpleDateFormat(Constant.DATE_FORMAT);
    }
}
