package com.example.ahmed.eyecare.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.ahmed.eyecare.R;
import com.example.ahmed.eyecare.fragment.MenuFragment;
import com.example.ahmed.eyecare.utils.Utils;

public class MenuContainer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_container);
        Utils.goToFragment(this, new MenuFragment(), null, null);
    }
}
