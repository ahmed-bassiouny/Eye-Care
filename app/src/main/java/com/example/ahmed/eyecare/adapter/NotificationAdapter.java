package com.example.ahmed.eyecare.adapter;

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
import com.example.ahmed.eyecare.model.Speaker;
import com.example.ahmed.eyecare.model.UserNotification;
import com.example.ahmed.eyecare.utils.Constant;
import com.example.ahmed.eyecare.utils.Utils;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ahmed on 11/10/17.
 */

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.CutomViewHolder> {


    List<UserNotification> userNotificationList;

    public NotificationAdapter(List<UserNotification> userNotificationList) {
        this.userNotificationList = userNotificationList;
    }

    @Override
    public NotificationAdapter.CutomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification, null);
        CutomViewHolder cutomViewHolder = new CutomViewHolder(view);
        return cutomViewHolder;
    }

    @Override
    public void onBindViewHolder(NotificationAdapter.CutomViewHolder holder, int position) {
        UserNotification userNotification = userNotificationList.get(position);
        holder.tvTitle.setText(userNotification.getTitle());
        holder.tvBody.setText(userNotification.getBody());
        holder.tvTime.setText(userNotification.getNotificationTime());
    }

    @Override
    public int getItemCount() {
        return userNotificationList.size();
    }

    class CutomViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle;
        private TextView tvBody;
        private TextView tvTime;
        public CutomViewHolder(View view) {
            super(view);
            tvTitle = view.findViewById(R.id.tv_title);
            tvBody = view.findViewById(R.id.tv_body);
            tvTime = view.findViewById(R.id.tv_time);
        }
    }

}
