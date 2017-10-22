package com.example.ahmed.eyecare.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ahmed.eyecare.R;
import com.example.ahmed.eyecare.fragment.SpeakerFragment;
import com.example.ahmed.eyecare.model.Comment;
import com.example.ahmed.eyecare.model.Speaker;
import com.example.ahmed.eyecare.utils.Constant;
import com.example.ahmed.eyecare.utils.Utils;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ahmed on 11/10/17.
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CutomViewHolder> {


    List<Comment> comments;
    Context context;
    public CommentAdapter(Context context,List<Comment> comments) {
        this.context=context;
        this.comments = comments;
    }

    @Override
    public CommentAdapter.CutomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_speaker, null);
        CutomViewHolder cutomViewHolder = new CutomViewHolder(view);
        return cutomViewHolder;
    }

    @Override
    public void onBindViewHolder(CommentAdapter.CutomViewHolder holder, int position) {
        Comment comment = comments.get(position);
        holder.tvName.setText(comment.getUserName());
        holder.tvComment.setText(comment.getComment());
        holder.tvDate.setText(comment.getCommentDate());

        if (!comment.getUserAvatar().isEmpty())
            Utils.setImage(context, comment.getUserAvatar(), holder.ivAvatar);
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    class CutomViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView ivAvatar;
        private TextView tvName;
        private TextView tvComment;
        private TextView tvDate;
        public CutomViewHolder(View view) {
            super(view);
            ivAvatar = view.findViewById(R.id.iv_avatar);
            tvName = view.findViewById(R.id.tv_name);
            tvComment = view.findViewById(R.id.tv_comment);
            tvDate = view.findViewById(R.id.tv_date);
        }
    }
    public void addComment(Comment comment){
        this.comments.add(comment);
        notifyItemInserted(this.comments.size()-1);
    }

}
