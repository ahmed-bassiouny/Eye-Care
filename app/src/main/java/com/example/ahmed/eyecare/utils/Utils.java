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
}
