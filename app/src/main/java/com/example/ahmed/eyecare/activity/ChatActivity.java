package com.example.ahmed.eyecare.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmed.eyecare.R;
import com.example.ahmed.eyecare.adapter.ChatAdapter;
import com.example.ahmed.eyecare.api.utils.RetrofitRequest;
import com.example.ahmed.eyecare.api.utils.RetrofitResponse;
import com.example.ahmed.eyecare.model.Message;

import java.util.List;

public class ChatActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ChatAdapter chatAdapter;
    ProgressBar progressBar;
    TextView tvSend;
    EditText edMessage;
    int otherUserId =0; // this id about user i chat with him
    String otherUserImage; //this image about user i chat with him

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        findViewById();
        loadData();
        onClick();
    }

    private void onClick() {
        tvSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edMessage.getText().toString().trim().isEmpty())
                    return;
            }
        });
    }

    private void findViewById() {
        recyclerView = (RecyclerView) findViewById(R.id.recycleview);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        tvSend = (TextView) findViewById(R.id.tv_send);
        edMessage = (EditText) findViewById(R.id.et_message);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(mLayoutManager);
    }


    private void loadData() {
        RetrofitRequest.getMessageDetails(13039, 12991, new RetrofitResponse<List<Message>>() {
            @Override
            public void onSuccess(List<Message> messages) {
                if(messages==null){
                    onFailed(getString(R.string.cant_load_data));
                    return;
                }
                chatAdapter = new ChatAdapter(ChatActivity.this,messages,"","");
                recyclerView.setAdapter(chatAdapter);
            }

            @Override
            public void onFailed(String errorMessage) {
                Toast.makeText(ChatActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
