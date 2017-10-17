package com.example.ahmed.eyecare.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ahmed.eyecare.R;
import com.example.ahmed.eyecare.interfaces.OnClickListenerAdapter;
import com.example.ahmed.eyecare.model.Session;

import java.util.List;

/**
 * Created by ahmed on 11/10/17.
 */

public class SessionAgendaAdapter extends RecyclerView.Adapter<SessionAgendaAdapter.CutomViewHolder> {


    List<Session> sessions;
    Context context;
    OnClickListenerAdapter onClickListenerAdapter;

    public SessionAgendaAdapter(Context context, OnClickListenerAdapter onClickListenerAdapter) {
        this.context = context;
        this.onClickListenerAdapter = onClickListenerAdapter;
    }

    @Override
    public SessionAgendaAdapter.CutomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_agenda, null);
        CutomViewHolder cutomViewHolder = new CutomViewHolder(view);
        return cutomViewHolder;
    }

    @Override
    public void onBindViewHolder(SessionAgendaAdapter.CutomViewHolder holder, final int position) {
        Session session = sessions.get(position);
        holder.tvSessionName.setText(session.getSessionName());
        holder.tvSessionLocation.setText(session.getLocation());
        holder.tvSessionSpeaker.setText(session.getSessioninterested() + " " + context.getString(R.string.people_interested));
        holder.tvSessionTime.setText(session.getFullTimeSession());
        if (session.isMyAgenda())
            holder.ivAddToMyAgenda.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.calendarred));
        else {
            holder.ivAddToMyAgenda.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.calendar));
            holder.ivAddToMyAgenda.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListenerAdapter.onClick(position);
                }
            });
        }
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
        private ImageView ivAddToMyAgenda;

        public CutomViewHolder(View view) {
            super(view);
            tvSessionName = view.findViewById(R.id.tv_session_name);
            tvSessionTime = view.findViewById(R.id.tv_session_time);
            tvSessionLocation = view.findViewById(R.id.tv_session_location);
            tvSessionSpeaker = view.findViewById(R.id.tv_session_speaker);
            ivAddToMyAgenda = view.findViewById(R.id.iv_add_to_my_agenda);

        }
    }

    public void setData(List<Session> sessions) {
        this.sessions = sessions;
    }
    public void updateData(List<Session> sessions) {
        this.sessions = sessions;
        notifyDataSetChanged();
    }
}
