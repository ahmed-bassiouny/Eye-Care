package com.example.ahmed.eyecare.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ahmed.eyecare.R;
import com.example.ahmed.eyecare.model.Speaker;
import com.example.ahmed.eyecare.utils.Utils;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ahmed on 11/10/17.
 */

public class SpeakerAdapter extends RecyclerView.Adapter<SpeakerAdapter.CutomViewHolder> {


    List<Speaker> speakers;
    Context context;

    public SpeakerAdapter(List<Speaker> speakers, Context context) {
        this.speakers = speakers;
        this.context = context;
    }

    @Override
    public SpeakerAdapter.CutomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_speaker, null);
        CutomViewHolder cutomViewHolder = new CutomViewHolder(view);
        return cutomViewHolder;
    }

    @Override
    public void onBindViewHolder(SpeakerAdapter.CutomViewHolder holder, int position) {

        Speaker speaker = speakers.get(position);
        holder.tvName.setText(speaker.getName());
        holder.tvPosition.setText(speaker.getPosition());
        holder.tvCompany.setText(speaker.getCompany());

        if (!speaker.getImage().isEmpty())
            Utils.setImage(context, speaker.getImage(), holder.ivAvatar);
    }

    @Override
    public int getItemCount() {
        return speakers.size();
    }

    class CutomViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvPosition, tvCompany;
        CircleImageView ivAvatar;

        public CutomViewHolder(View view) {
            super(view);
            tvName = view.findViewById(R.id.tv_name);
            tvPosition = view.findViewById(R.id.tv_position);
            tvCompany = view.findViewById(R.id.tv_company);
        }
    }
}
