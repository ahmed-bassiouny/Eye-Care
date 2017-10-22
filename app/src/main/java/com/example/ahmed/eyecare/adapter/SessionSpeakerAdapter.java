package com.example.ahmed.eyecare.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ahmed.eyecare.R;
import com.example.ahmed.eyecare.fragment.SessionFragment;
import com.example.ahmed.eyecare.model.Session;
import com.example.ahmed.eyecare.utils.Constant;
import com.example.ahmed.eyecare.utils.Utils;

import java.util.List;

/**
 * Created by ahmed on 11/10/17.
 */

public class SessionSpeakerAdapter extends RecyclerView.Adapter<SessionSpeakerAdapter.CutomViewHolder> {


    List<Session> sessions;
    FragmentActivity fragmentActivity;
    public SessionSpeakerAdapter(List<Session> sessions , FragmentActivity fragmentActivity) {
        this.sessions = sessions;
        this.fragmentActivity=fragmentActivity;
    }

    @Override
    public SessionSpeakerAdapter.CutomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_session, parent,false);
        CutomViewHolder cutomViewHolder = new CutomViewHolder(view);
        return cutomViewHolder;
    }

    @Override
    public void onBindViewHolder(SessionSpeakerAdapter.CutomViewHolder holder, int position) {
        final Session session = sessions.get(position);
        holder.tvSessionName.setText(session.getSessionName());
        holder.tvSessionLocation.setText(session.getLocation());
        holder.tvSessionSpeaker.setText(session.getSessioninterested()+" "+fragmentActivity.getString(R.string.people_interested));
        holder.tvSessionTime.setText(session.getFullTimeSession());
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putSerializable(Constant.INTENT_SESSION_KEY,  session);
                Utils.goToFragment(fragmentActivity,new SessionFragment(),"Back",bundle);
            }
        });
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
        private FrameLayout container;

        public CutomViewHolder(View view) {
            super(view);
            tvSessionName = view.findViewById(R.id.tv_session_name);
            tvSessionTime = view.findViewById(R.id.tv_session_time);
            tvSessionLocation = view.findViewById(R.id.tv_session_location);
            tvSessionSpeaker = view.findViewById(R.id.tv_session_speaker);
            container = view.findViewById(R.id.container);
        }
    }
}
