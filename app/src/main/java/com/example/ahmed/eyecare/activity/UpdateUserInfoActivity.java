package com.example.ahmed.eyecare.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ahmed.eyecare.R;
import com.example.ahmed.eyecare.api.utils.RetrofitRequest;
import com.example.ahmed.eyecare.api.utils.RetrofitResponse;
import com.example.ahmed.eyecare.utils.SharedPref;

public class UpdateUserInfoActivity extends AppCompatActivity {

    EditText etEmail, etCountry, etMobile, etHospital;
    Button btnProceed;
    ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user_info);
        findViewById();
        onClick();
    }

    private void onClick() {
        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etEmail.getText().toString().trim().isEmpty()) {
                    etEmail.setError(getString(R.string.insert_data));
                    return;
                }
                progress.setVisibility(View.VISIBLE);
                btnProceed.setEnabled(false);
                RetrofitRequest.updateUserInfo(SharedPref.getMyAccount(UpdateUserInfoActivity.this).getUserId(), etCountry.getText().toString(),
                        etEmail.getText().toString(), etMobile.getText().toString(), etHospital.getText().toString(), new RetrofitResponse<String >() {
                            @Override
                            public void onSuccess(String s) {
                                Toast.makeText(UpdateUserInfoActivity.this, s, Toast.LENGTH_SHORT).show();
                                SharedPref.setSubInfo(UpdateUserInfoActivity.this,etCountry.getText().toString(),etHospital.getText().toString(),
                                        etMobile.getText().toString(),etEmail.getText().toString());
                                startActivity(new Intent(UpdateUserInfoActivity.this,MainContainer.class));
                                finish();
                            }

                            @Override
                            public void onFailed(String errorMessage) {
                                Toast.makeText(UpdateUserInfoActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                                btnProceed.setEnabled(true);
                            }
                        });
            }
        });
    }

    private void findViewById() {
        etEmail = (EditText) findViewById(R.id.et_email);
        etCountry = (EditText) findViewById(R.id.et_country);
        etMobile = (EditText) findViewById(R.id.et_mobile);
        etHospital = (EditText) findViewById(R.id.et_hospital);
        btnProceed = (Button) findViewById(R.id.btn_proceed);
        progress = (ProgressBar) findViewById(R.id.progress);
    }
}
