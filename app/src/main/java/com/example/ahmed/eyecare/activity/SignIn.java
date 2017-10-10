package com.example.ahmed.eyecare.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ahmed.eyecare.R;
import com.example.ahmed.eyecare.api.utils.RetrofitRequest;
import com.example.ahmed.eyecare.api.utils.RetrofitResponse;
import com.example.ahmed.eyecare.model.User;

public class SignIn extends AppCompatActivity {

    EditText etEmail,etCode;
    Button btnSignin;
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
                    etEmail.setError(getString(R.string.please_enter_email));
                }else if(etCode.getText().toString().trim().isEmpty()){
                    etCode.setError(getString(R.string.please_enter_code));
                }else {
                    // TODO send data to server
                    RetrofitRequest.login(etEmail.getText().toString(), etCode.getText().toString(), new RetrofitResponse<User>() {
                        @Override
                        public void onSuccess(User user) {
                            Toast.makeText(SignIn.this, "Done", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailed(String errorMessage) {
                            Toast.makeText(SignIn.this, errorMessage, Toast.LENGTH_SHORT).show();
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
    }

}
