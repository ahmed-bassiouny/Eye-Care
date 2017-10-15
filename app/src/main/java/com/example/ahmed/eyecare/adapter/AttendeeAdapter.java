package com.example.ahmed.eyecare.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ahmed.eyecare.R;
import com.example.ahmed.eyecare.model.Session;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ahmed on 11/10/17.
 */

public class AttendeeAdapter extends RecyclerView.Adapter<AttendeeAdapter.CutomViewHolder> {


    public AttendeeAdapter() {

    }

    @Override
    public AttendeeAdapter.CutomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_attendee, null);
        CutomViewHolder cutomViewHolder = new CutomViewHolder(view);
        return cutomViewHolder;
    }

    @Override
    public void onBindViewHolder(AttendeeAdapter.CutomViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class CutomViewHolder extends RecyclerView.ViewHolder {
        private TextView tvLetter;
        private CircleImageView ivAvatar;
        private TextView tvName;
        private TextView tvPosition;
        private TextView tvCompany;
        private ImageView ivChat;

        public CutomViewHolder(View view) {
            super(view);
            tvLetter = view.findViewById(R.id.tv_letter);
            ivAvatar = view.findViewById(R.id.iv_avatar);
            tvName = view.findViewById(R.id.tv_name);
            tvPosition = view.findViewById(R.id.tv_position);
            tvCompany = view.findViewById(R.id.tv_company);
            ivChat = view.findViewById(R.id.iv_chat);
        }
    }
}
