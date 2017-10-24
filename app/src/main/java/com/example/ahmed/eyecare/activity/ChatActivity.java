package com.example.ahmed.eyecare.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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
import com.example.ahmed.eyecare.utils.Constant;
import com.example.ahmed.eyecare.utils.MyAccount;
import com.example.ahmed.eyecare.utils.SharedPref;
import com.example.ahmed.eyecare.utils.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

public class ChatActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ChatAdapter chatAdapter;
    ProgressBar progressBar;
    TextView tvSend;
    EditText edMessage;
    private Toolbar mToolbar;
    int myId = 0;
    String myImage = "";
    int otherUserId = 0; // this id about user i chat with him
    String otherUserImage = ""; //this image about user i chat with him

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        findViewById();
        getData();
        onClick();
        makeRequestWithHandler();
        updateMessageStatus();
        getSupportActionBar().setTitle("Chat");
    }

    private void getData() {
        MyAccount myAccount = SharedPref.getMyAccount(this);
        myId = myAccount.getUserId();
        myImage = myAccount.getUserImage();
        otherUserId = getIntent().getIntExtra(Constant.INTENT_ANOTHER_USER_ID_KEY, 0);
        otherUserImage = getIntent().getStringExtra(Constant.INTENT_ANOTHER_USER_IMAGE_KEY);

    }

    private void onClick() {
        tvSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edMessage.getText().toString().trim().isEmpty())
                    return;
                Date now = new Date();
                String strDate = Utils.getSimpleDateFormate().format(now);
                Message message = new Message();
                message.setMessage(edMessage.getText().toString());
                message.setMyMessage(true);
                message.setMessageTime(strDate);
                final int index = chatAdapter.addMessage(message);
                recyclerView.scrollToPosition(chatAdapter.getItemCount() - 1);
                if (otherUserId == 0)
                    return;
                RetrofitRequest.sendMessage(myId, otherUserId, edMessage.getText().toString(), new RetrofitResponse<Boolean>() {
                    @Override
                    public void onSuccess(Boolean aBoolean) {
                        if (!aBoolean) {
                            onFailed("");
                        } else {
                            edMessage.setText("");
                        }
                    }

                    @Override
                    public void onFailed(String errorMessage) {
                        chatAdapter.removeMessage(index);
                        Toast.makeText(ChatActivity.this, R.string.cant_send_message, Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }

    private void findViewById() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mToolbar.setNavigationIcon(R.drawable.ic_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
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
        if (otherUserId == 0) {
            Toast.makeText(ChatActivity.this, getString(R.string.cant_load_data), Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            return;
        }
        RetrofitRequest.getMessageDetails(myId, otherUserId, new RetrofitResponse<List<Message>>() {
            @Override
            public void onSuccess(List<Message> messages) {
                if (messages == null) {
                    messages = new ArrayList<>();
                    onFailed(getString(R.string.cant_load_data));
                }
                Collections.reverse(messages);
                chatAdapter = new ChatAdapter(ChatActivity.this, messages, myImage, otherUserImage);
                recyclerView.setAdapter(chatAdapter);
                recyclerView.scrollToPosition(chatAdapter.getItemCount() - 1);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailed(String errorMessage) {
                Toast.makeText(ChatActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);

            }
        });
    }

    private void makeRequestWithHandler() {
        new Thread(new TimerTask() {
            @Override
            public void run() {
                while (true) {
                    try {
                        loadData();
                        Thread.sleep(Constant.REQUEST_PER_SECOND);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
    // this method make message read from me
    private void updateMessageStatus(){
        RetrofitRequest.updateMessageStatus(myId, otherUserId, new RetrofitResponse<Boolean>() {
            @Override
            public void onSuccess(Boolean aBoolean) {

            }

            @Override
            public void onFailed(String errorMessage) {
                Toast.makeText(ChatActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
