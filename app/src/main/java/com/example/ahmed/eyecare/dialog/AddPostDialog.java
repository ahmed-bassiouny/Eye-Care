package com.example.ahmed.eyecare.dialog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ahmed.eyecare.R;
import com.example.ahmed.eyecare.api.utils.RetrofitRequest;
import com.example.ahmed.eyecare.api.utils.RetrofitResponse;
import com.example.ahmed.eyecare.model.Post;
import com.example.ahmed.eyecare.utils.DummyData;

public class AddPostDialog extends AppCompatActivity {

    public static final String POST ="post";

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
                RetrofitRequest.addPost(DummyData.userID,edPost.getText().toString(), new RetrofitResponse<Post>() {
                    @Override
                    public void onSuccess(Post post) {
                        Toast.makeText(AddPostDialog.this, R.string.thanks, Toast.LENGTH_SHORT).show();
                        Intent resultIntent = new Intent();
                        resultIntent.putExtra(POST,post);
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
