package com.example.ahmed.eyecare.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ahmed.eyecare.R;
import com.example.ahmed.eyecare.fragment.SpeakerListFragment;
import com.example.ahmed.eyecare.utils.Utils;

public class MenuContainer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_container);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Utils.goToFragment(this,new SpeakerListFragment(),null,null);
    }
}
