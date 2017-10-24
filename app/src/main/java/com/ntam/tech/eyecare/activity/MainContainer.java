package com.ntam.tech.eyecare.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ntam.tech.eyecare.R;
import com.ntam.tech.eyecare.fragment.NewsFeedFragment;
import com.ntam.tech.eyecare.utils.Utils;

public class MainContainer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_container);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Utils.goToFragment(this,new NewsFeedFragment(),null,null);
    }
}
