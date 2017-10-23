package com.example.ahmed.eyecare.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ahmed.eyecare.R;
import com.example.ahmed.eyecare.utils.SharedPref;

public class Splash extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 2000; // 2 second

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
