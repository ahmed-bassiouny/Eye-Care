package com.ntam.tech.eyecare.adapter;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ntam.tech.eyecare.R;
import com.ntam.tech.eyecare.fragment.SpeakerFragment;
import com.ntam.tech.eyecare.model.Speaker;
import com.ntam.tech.eyecare.utils.Constant;
import com.ntam.tech.eyecare.utils.Utils;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ahmed on 11/10/17.
 */

public class SpeakerAdapter extends RecyclerView.Adapter<SpeakerAdapter.CutomViewHolder> {


    List<Speaker> speakers;
    FragmentActivity activity;
    int color;

    public SpeakerAdapter(List<Speaker> speakers, FragmentActivity activity,int color) {
        this.speakers = speakers;
        this.activity = activity;
        this.color=color;
    }

    @Override
    public SpeakerAdapter.CutomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_speaker, null);
        CutomViewHolder cutomViewHolder = new CutomViewHolder(view);
        return cutomViewHolder;
    }

    @Override
    public void onBindViewHolder(SpeakerAdapter.CutomViewHolder holder, int position) {

        final Speaker speaker = speakers.get(position);
            holder.tvName.setText(speaker.getName());
            holder.tvName.setTextColor(ContextCompat.getColor(activity,color));
            holder.tvPosition.setText(speaker.getPosition());
            holder.tvPosition.setTextColor(ContextCompat.getColor(activity,color));
            holder.tvCompany.setText(speaker.getCompany());
            holder.tvCompany.setTextColor(ContextCompat.getColor(activity,color));

        if (!speaker.getImage().isEmpty())
            Utils.setImage(activity, speaker.getImage(), holder.ivAvatar);
        holder.relativeLayoutContianer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constant.INTENT_OPEN_SPEAKER_KEY,speaker);
                Utils.goToFragment(activity,new SpeakerFragment(),"Back",bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return speakers.size();
    }

    class CutomViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvPosition, tvCompany;
        CircleImageView ivAvatar;
        RelativeLayout relativeLayoutContianer;
        public CutomViewHolder(View view) {
            super(view);
            tvName = view.findViewById(R.id.tv_name);
            tvPosition = view.findViewById(R.id.tv_position);
            tvCompany = view.findViewById(R.id.tv_company);
            ivAvatar=view.findViewById(R.id.iv_avatar);
            relativeLayoutContianer = view.findViewById(R.id.relative_container);
        }
    }
    public void updateList(List<Speaker> speakers){
        this.speakers=speakers;
        notifyDataSetChanged();
    }
}
