package com.example.ahmed.eyecare.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ahmed.eyecare.R;

/**
 * Created by ahmed on 11/10/17.
 */

public class NewsFeedAdapter extends RecyclerView.Adapter<NewsFeedAdapter.CutomViewHolder> {


    public NewsFeedAdapter() {

    }

    @Override
    public NewsFeedAdapter.CutomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_newfeed, null);
        CutomViewHolder cutomViewHolder = new CutomViewHolder(view);
        return cutomViewHolder;
    }

    @Override
    public void onBindViewHolder(NewsFeedAdapter.CutomViewHolder holder, int position) {

        // TODO : SET DATA
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class CutomViewHolder extends RecyclerView.ViewHolder {

        TextView tvDay, tvMonth ,tvUserName ,tvUserPost , ivTime , ivComment ,ivLike;
        ImageView ivAvatar;

        public CutomViewHolder(View view) {
            super(view);
            tvDay = view.findViewById(R.id.tv_day);
            tvMonth = view.findViewById(R.id.tv_month);
            tvUserName = view.findViewById(R.id.tv_user_name);
            tvUserPost = view.findViewById(R.id.tv_user_post);
            ivTime = view.findViewById(R.id.tv_time);
            ivComment = view.findViewById(R.id.tv_comment);
            ivLike = view.findViewById(R.id.tv_like);
            ivAvatar = view.findViewById(R.id.iv_avatar);
        }
    }
}
