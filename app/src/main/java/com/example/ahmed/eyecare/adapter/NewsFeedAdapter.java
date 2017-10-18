package com.example.ahmed.eyecare.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ahmed.eyecare.R;
import com.example.ahmed.eyecare.interfaces.OnClickListenerAdapter;
import com.example.ahmed.eyecare.interfaces.OnClickPostAdapter;
import com.example.ahmed.eyecare.model.Post;
import com.example.ahmed.eyecare.utils.Utils;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ahmed on 11/10/17.
 */

public class NewsFeedAdapter extends RecyclerView.Adapter<NewsFeedAdapter.CutomViewHolder> {


    List<Post> posts;
    Context context;
    OnClickPostAdapter onClickListenerAdapter;
    public NewsFeedAdapter(List<Post> posts , Context context, Fragment fragment) {
        this.posts=posts;
        this.context=context;
        this.onClickListenerAdapter= (OnClickPostAdapter) fragment;
    }

    @Override
    public NewsFeedAdapter.CutomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_newfeed, null);
        CutomViewHolder cutomViewHolder = new CutomViewHolder(view);
        return cutomViewHolder;
    }

    @Override
    public void onBindViewHolder(NewsFeedAdapter.CutomViewHolder holder, final int position) {

        Post post = posts.get(position);
        holder.tvDay.setText(post.getDayDate());
        holder.tvMonth.setText(post.getMonth());

        holder.tvUserName.setText(post.getUserName());
        holder.tvUserPost.setText(post.getPost());
        holder.ivTime.setText(post.getTime());
        holder.ivComment.setText(post.getCommentsSize());
        holder.ivLike.setText(post.getNumberOfLike());
        if(post.getIsMakeLike()){
            holder.ivLike.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(context,R.drawable.vectorsmartred),null,null,null);
        }else {
            holder.ivLike.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(context,R.drawable.vectorsmart),null,null,null);
            holder.ivLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListenerAdapter.likePost(position);
                }
            });
        }
        if(!post.getAvatar().isEmpty())
            Utils.setImage(context,post.getAvatar(),holder.ivAvatar);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void updateAttendee(List<Post> postList) {
        this.posts=postList;
        notifyDataSetChanged();
    }

    class CutomViewHolder extends RecyclerView.ViewHolder {

        TextView tvDay, tvMonth ,tvUserName ,tvUserPost , ivTime , ivComment ,ivLike;
        CircleImageView ivAvatar;

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
    public void addPost(Post post){
        this.posts.add(0,post);
        notifyDataSetChanged();
    }
}
