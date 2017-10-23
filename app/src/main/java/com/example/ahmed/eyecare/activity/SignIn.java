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
import com.example.ahmed.eyecare.model.User;
import com.example.ahmed.eyecare.utils.SharedPref;

public class SignIn extends AppCompatActivity {

    EditText etEmail,etCode;
    Button btnSignin;
    ProgressBar progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        // init views
        findViewById();
        // handle click on views;
        onClick();
    }

    private void onClick() {
        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etEmail.getText().toString().trim().isEmpty()){
                    etEmail.setError(getString(R.string.please_enter_username));
                }else if(etCode.getText().toString().trim().isEmpty()){
                    etCode.setError(getString(R.string.please_enter_code));
                }else {
                    startLogin(true);
                    String token = SharedPref.getMyAccount(SignIn.this).getToken();
                    if(token.isEmpty()){
                        Toast.makeText(SignIn.this, getString(R.string.reinstall_app), Toast.LENGTH_SHORT).show();
                        startLogin(false);
                        return;
                    }
                    RetrofitRequest.login(etEmail.getText().toString(), etCode.getText().toString(),token, new RetrofitResponse<User>() {
                        @Override
                        public void onSuccess(User user) {
                            if(user !=null){
                                SharedPref.setMainInfo(SignIn.this,user.getId(),user.getName(),user.getImageUrl(),user.getEmail(),user.getBio(),user.isAdmin(),user.getPosition());
                                if(user.getEmail().isEmpty()) {
                                    startActivity(new Intent(SignIn.this, UpdateUserInfoActivity.class));
                                }else {
                                    startActivity(new Intent(SignIn.this,MenuContainer.class));
                                }
                                finish();
                            }else {
                                onFailed(getString(R.string.cant_load_data));
                            }
                        }

                        @Override
                        public void onFailed(String errorMessage) {
                            Toast.makeText(SignIn.this, errorMessage, Toast.LENGTH_SHORT).show();
                            startLogin(false);
                        }
                    });
                }
            }
        });
    }

    private void findViewById(){
        etEmail = (EditText)findViewById(R.id.et_email);
        etCode = (EditText)findViewById(R.id.et_code);
        btnSignin = (Button) findViewById(R.id.btn_signin);
        progress = (ProgressBar) findViewById(R.id.progress);
    }
    private void startLogin(boolean start){
        if(start) {
            btnSignin.setEnabled(false);
            progress.setVisibility(View.VISIBLE);
        }else {
            btnSignin.setEnabled(true);
            progress.setVisibility(View.GONE);
        }
    }

}
