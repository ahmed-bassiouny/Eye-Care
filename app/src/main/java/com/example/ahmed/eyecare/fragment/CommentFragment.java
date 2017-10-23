package com.example.ahmed.eyecare.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ahmed.eyecare.R;
import com.example.ahmed.eyecare.adapter.CommentAdapter;
import com.example.ahmed.eyecare.api.utils.RetrofitRequest;
import com.example.ahmed.eyecare.api.utils.RetrofitResponse;
import com.example.ahmed.eyecare.model.Comment;
import com.example.ahmed.eyecare.utils.Constant;
import com.example.ahmed.eyecare.utils.SharedPref;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CommentFragment extends Fragment {


    RecyclerView recycleview;
    EditText etComment;
    ImageView ivSend;
    CommentAdapter commentAdapter;
    List<Comment> comments;
    public static final int COMMENT_POST = 1;
    public static final int COMMENT_PHOTO = 2;
    private int type = 0;
    private int userId;
    private int itemId;

    public CommentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_comment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViewById(view);
        onClick();
    }

    private void onClick() {
        ivSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etComment.getText().toString().trim().isEmpty())
                    return;
                if (type == COMMENT_POST) {
                    RetrofitRequest.addCommentToPost(userId, etComment.getText().toString(), itemId, new RetrofitResponse<Comment>() {
                        @Override
                        public void onSuccess(Comment comment) {
                            if (commentAdapter != null)
                                commentAdapter.addComment(comment);
                        }

                        @Override
                        public void onFailed(String errorMessage) {
                            Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
                        }
                    });
                } else if (type == COMMENT_PHOTO) {
                    RetrofitRequest.addCommentToPhoto(userId, etComment.getText().toString(), itemId, new RetrofitResponse<Comment>() {
                        @Override
                        public void onSuccess(Comment comment) {
                            if (commentAdapter != null) {
                                comment.setCommentDate(new SimpleDateFormat(Constant.DATE_TIME_FORMAT).format(new Date()));
                                comment.setUserAvatar(SharedPref.getMyAccount(getContext()).getUserImage());
                                commentAdapter.addComment(comment);
                            }
                        }

                        @Override
                        public void onFailed(String errorMessage) {
                            Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                etComment.setText("");
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        comments = (List<Comment>) getArguments().getSerializable(Constant.INTENT_SHOW_COMMENT_KEY);
        type = getArguments().getInt(Constant.INTENT_COMMENT_TYPE);
        userId = SharedPref.getMyAccount(getContext()).getUserId();
        itemId = getArguments().getInt(Constant.INTENT_ITEM_ID_TYPE);
        if (comments != null) {
            commentAdapter = new CommentAdapter(getContext(), comments);
            recycleview.setAdapter(commentAdapter);
        }
    }

    private void findViewById(View view) {
        recycleview = view.findViewById(R.id.recycleview);
        etComment = view.findViewById(R.id.et_comment);
        ivSend = view.findViewById(R.id.iv_send);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recycleview.setLayoutManager(linearLayoutManager);
    }
}
