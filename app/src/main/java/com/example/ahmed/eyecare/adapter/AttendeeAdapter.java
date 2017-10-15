package com.example.ahmed.eyecare.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ahmed.eyecare.R;
import com.example.ahmed.eyecare.model.Attendee;
import com.example.ahmed.eyecare.model.Session;
import com.example.ahmed.eyecare.utils.Utils;

import java.util.ArrayList;
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
        Attendee currentAttendee = attendees.get(position);
        holder.tvName.setText(currentAttendee.getName());
        holder.tvCompany.setText(currentAttendee.getCompany());
        holder.tvPosition.setText(currentAttendee.getPosition());

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
