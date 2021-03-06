package com.ntam.tech.eyecare.dialog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ntam.tech.eyecare.R;
import com.ntam.tech.eyecare.api.utils.RetrofitRequest;
import com.ntam.tech.eyecare.api.utils.RetrofitResponse;
import com.ntam.tech.eyecare.model.Post;
import com.ntam.tech.eyecare.utils.Constant;
import com.ntam.tech.eyecare.utils.SharedPref;

public class AddPostDialog extends AppCompatActivity {


    EditText edPost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post_dialog);
        this.setFinishOnTouchOutside(false);
        findViewById();
        onClick();
    }

    private void findViewById(){
        edPost= (EditText) findViewById(R.id.ed_post);
    }
    private void onClick() {
        findViewById(R.id.btn_post).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edPost.getText().toString().trim().isEmpty())
                    return;
                RetrofitRequest.addPost(SharedPref.getMyAccount(AddPostDialog.this).getUserId(),edPost.getText().toString(), new RetrofitResponse<Post>() {
                    @Override
                    public void onSuccess(Post post) {
                        Toast.makeText(AddPostDialog.this, R.string.thanks, Toast.LENGTH_SHORT).show();
                        Intent resultIntent = new Intent();
                        resultIntent.putExtra(Constant.INTENT_POST_KEY,post);
                        setResult(AddPostDialog.this.RESULT_OK, resultIntent);
                        finish();
                    }

                    @Override
                    public void onFailed(String errorMessage) {
                        Toast.makeText(AddPostDialog.this, errorMessage, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
