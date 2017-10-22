package com.example.ahmed.eyecare.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.example.ahmed.eyecare.R;

public class UpdateUserInfoActivity extends AppCompatActivity {

    EditText etEmail,etCountry,etMobile,etHospital;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user_info);
        findViewById();
    }

    private void findViewById() {
        etEmail = (EditText) findViewById(R.id.et_email);
        etCountry =(EditText) findViewById(R.id.et_country);
        etMobile=(EditText) findViewById(R.id.et_mobile);
        etHospital=(EditText) findViewById(R.id.et_hospital);
    }
}
