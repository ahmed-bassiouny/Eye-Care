package com.example.ahmed.eyecare.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ahmed.eyecare.R;
import com.example.ahmed.eyecare.model.Session;

import java.util.List;

/**
 * Created by ahmed on 11/10/17.
 */

public class SessionSpeakerAdapter extends RecyclerView.Adapter<SessionSpeakerAdapter.CutomViewHolder> {


    List<Session> sessions;
    Context context;
    public SessionSpeakerAdapter(List<Session> sessions , Context context) {
        this.sessions = sessions;
        this.context=context;
    }

    @Override
    public SessionSpeakerAdapter.CutomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_session, null);
        CutomViewHolder cutomViewHolder = new CutomViewHolder(view);
        return cutomViewHolder;
    }

    @Override
    public void onBindViewHolder(SessionSpeakerAdapter.CutomViewHolder holder, int position) {
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
