package com.ntam.tech.eyecare.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ntam.tech.eyecare.R;
import com.ntam.tech.eyecare.activity.ChatActivity;
import com.ntam.tech.eyecare.model.Attendee;
import com.ntam.tech.eyecare.utils.Constant;
import com.ntam.tech.eyecare.utils.Utils;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ahmed on 11/10/17.
 */

public class AttendeeAdapter extends RecyclerView.Adapter<AttendeeAdapter.CutomViewHolder>  {


    private List<Attendee> attendees;
    private Context context;

    public AttendeeAdapter(List<Attendee> attendees, Context context) {
        this.attendees = attendees;
        this.context = context;
    }

    @Override
    public AttendeeAdapter.CutomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_attendee, null);
        CutomViewHolder cutomViewHolder = new CutomViewHolder(view);
        return cutomViewHolder;
    }

    @Override
    public void onBindViewHolder(AttendeeAdapter.CutomViewHolder holder, int position) {
        final Attendee currentAttendee = attendees.get(position);
        if(currentAttendee.getName().isEmpty()){
            holder.tvName.setVisibility(View.GONE);
        }else {
            holder.tvName.setVisibility(View.VISIBLE);
            holder.tvName.setText(currentAttendee.getName());
        }
        if(currentAttendee.getCompany().isEmpty()){
            holder.tvCompany.setVisibility(View.GONE);
        }else {
            holder.tvCompany.setVisibility(View.VISIBLE);
            holder.tvCompany.setText(currentAttendee.getCompany());
        }
        if(currentAttendee.getPosition().isEmpty()){
            holder.tvPosition.setVisibility(View.GONE);
        }else {
            holder.tvPosition.setVisibility(View.VISIBLE);
            holder.tvPosition.setText(currentAttendee.getPosition());
        }


        if (!currentAttendee.getImage().isEmpty())
            Utils.setImage(context, currentAttendee.getImage(), holder.ivAvatar);
        if(position == 0){
            holder.tvLetter.setVisibility(View.VISIBLE);
            holder.tvLetter.setText(currentAttendee.getName().charAt(0)+"");
        }else {
            Attendee prevAttende = attendees.get(position-1);
            if(prevAttende.getName().toLowerCase().charAt(0) == currentAttendee.getName().toLowerCase().charAt(0)){
                holder.tvLetter.setVisibility(View.GONE);
            }else {
                holder.tvLetter.setVisibility(View.VISIBLE);
                holder.tvLetter.setText(currentAttendee.getName().charAt(0)+"");
            }
        }
        holder.ivChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context, ChatActivity.class);
                intent.putExtra(Constant.INTENT_ANOTHER_USER_ID_KEY,currentAttendee.getId());
                intent.putExtra(Constant.INTENT_ANOTHER_USER_IMAGE_KEY,currentAttendee.getImage());
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return attendees.size();
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
    public void updateAttendee(List<Attendee> item){
        this.attendees.addAll(item);
        notifyDataSetChanged();
    }
}
