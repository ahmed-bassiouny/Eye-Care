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
import com.example.ahmed.eyecare.model.Session;
import com.example.ahmed.eyecare.model.Speaker;
import com.example.ahmed.eyecare.utils.Constant;
import com.example.ahmed.eyecare.utils.Utils;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ahmed on 11/10/17.
 */

public class SessionAdapter extends RecyclerView.Adapter<SessionAdapter.CutomViewHolder> {


    List<Session> sessions;
    Context context;
    public SessionAdapter(List<Session> sessions ,Context context) {
        this.sessions = sessions;
        this.context=context;
    }

    @Override
    public SessionAdapter.CutomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_session, null);
        CutomViewHolder cutomViewHolder = new CutomViewHolder(view);
        return cutomViewHolder;
    }

    @Override
    public void onBindViewHolder(SessionAdapter.CutomViewHolder holder, int position) {
        Session session = sessions.get(position);
        holder.tvSessionName.setText(session.getSessionName());
        holder.tvSessionLocation.setText(session.getLocation());
        holder.tvSessionSpeaker.setText(session.getspeakerCount()+" "+context.getString(R.string.people_interested));
        holder.tvSessionTime.setText(session.getFullTimeSession());
    }

    @Override
    public int getItemCount() {
        return sessions.size();
    }

    class CutomViewHolder extends RecyclerView.ViewHolder {
        private TextView tvSessionName;
        private TextView tvSessionTime;
        private TextView tvSessionLocation;
        private TextView tvSessionSpeaker;

        public CutomViewHolder(View view) {
            super(view);
            tvSessionName = view.findViewById(R.id.tv_session_name);
            tvSessionTime = view.findViewById(R.id.tv_session_time);
            tvSessionLocation = view.findViewById(R.id.tv_session_location);
            tvSessionSpeaker = view.findViewById(R.id.tv_session_speaker);
        }
    }
}
