package com.ntam.tech.eyecare.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.crashlytics.android.Crashlytics;
import com.ntam.tech.eyecare.R;
import com.ntam.tech.eyecare.utils.SharedPref;
import io.fabric.sdk.android.Fabric;

public class Splash extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 2000; // 2 second

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (SharedPref.getMyAccount(Splash.this).getUserId() > 0) {
                    if (SharedPref.getMyAccount(Splash.this).getEmail().isEmpty()) {
                        startActivity(new Intent(Splash.this, UpdateUserInfoActivity.class));
                    } else {
                        startActivity(new Intent(Splash.this, MenuContainer.class));
                    }
                } else {
                    Intent mainIntent = new Intent(Splash.this, SignIn.class);
                    startActivity(mainIntent);
                }
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
