package com.example.ahmed.eyecare.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ahmed.eyecare.R;
import com.example.ahmed.eyecare.fragment.AgendaFragment;
import com.example.ahmed.eyecare.utils.Utils;

public class Agenda extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);
        Utils.goToFragment(this,new AgendaFragment(),null,null);
    }
}
