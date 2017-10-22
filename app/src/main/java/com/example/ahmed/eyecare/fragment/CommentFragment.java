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
import android.widget.ProgressBar;

import com.example.ahmed.eyecare.R;
import com.example.ahmed.eyecare.adapter.CommentAdapter;
import com.example.ahmed.eyecare.model.Comment;
import com.example.ahmed.eyecare.utils.Constant;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CommentFragment extends Fragment {


    RecyclerView recycleview;
    ProgressBar progress;
    EditText etComment;
    ImageView ivSend;
    CommentAdapter commentAdapter;
    List<Comment> comments;

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
    }

    @Override
    public void onStart() {
        super.onStart();
        comments = (List<Comment>) getArguments().getSerializable(Constant.INTENT_SHOW_COMMENT_KEY);
        if (comments != null)
            commentAdapter = new CommentAdapter(getContext(), comments);
    }

    private void findViewById(View view) {
        recycleview = view.findViewById(R.id.recycleview);
        progress = view.findViewById(R.id.progress);
        etComment = view.findViewById(R.id.et_comment);
        ivSend = view.findViewById(R.id.iv_send);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recycleview.setLayoutManager(linearLayoutManager);
    }
}
