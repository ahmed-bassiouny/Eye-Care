package com.ntam.tech.eyecare.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ntam.tech.eyecare.R;
import com.ntam.tech.eyecare.interfaces.OnClickListenerAdapter;
import com.ntam.tech.eyecare.model.Chat;
import com.ntam.tech.eyecare.utils.Utils;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ahmed on 11/10/17.
 */

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.CutomViewHolder> {


    List<Chat> chatList;
    Context context;
    OnClickListenerAdapter onClickListenerAdapter;
    public ChatListAdapter(List<Chat> chatList, Context context,OnClickListenerAdapter onClickListenerAdapter) {
        this.chatList = chatList;
        this.context=context;
        this.onClickListenerAdapter=onClickListenerAdapter;
    }

    @Override
    public ChatListAdapter.CutomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_list, null);
        CutomViewHolder cutomViewHolder = new CutomViewHolder(view);
        return cutomViewHolder;
    }

    @Override
    public void onBindViewHolder(ChatListAdapter.CutomViewHolder holder, final int position) {

        Chat chat = chatList.get(position);
        holder.tvName.setText(chat.getName());
        holder.tvLastMessage.setText(chat.getMessage());
        holder.tvTime.setText(chat.getTime());

        if (!chat.getImage().isEmpty())
            Utils.setImage(context, chat.getImage(), holder.ivAvatar);
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListenerAdapter.onClick(position);
            }
        });
        if(chat.isUnread()){
            holder.viewUnread.setVisibility(View.VISIBLE);
        }else {
            holder.viewUnread.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }

    class CutomViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView ivAvatar;
        private TextView tvName;
        private TextView tvLastMessage;
        private TextView tvTime;
        RelativeLayout relativeLayout;
        private View viewUnread;
        public CutomViewHolder(View view) {
            super(view);
            ivAvatar = view.findViewById(R.id.iv_avatar);
            tvName = view.findViewById(R.id.tv_name);
            tvLastMessage = view.findViewById(R.id.tv_last_message);
            tvTime = view.findViewById(R.id.tv_time);
            relativeLayout=view.findViewById(R.id.container);
            viewUnread = view.findViewById(R.id.view_unread);
        }
    }
}
