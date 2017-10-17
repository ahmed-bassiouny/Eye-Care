package com.example.ahmed.eyecare.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ahmed.eyecare.R;
import com.example.ahmed.eyecare.model.Message;
import com.example.ahmed.eyecare.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by bassiouny on 17/10/17.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.CutomViewHolder> {

    List<Message> messageList;
    Context context;
    String myImage,userImage;
    public ChatAdapter(Context context, List<Message> messageList, String myImage, String userImage){
        this.context=context;
        this.messageList=messageList;
        this.myImage=myImage;
        this.userImage=userImage;
    }
    @Override
    public ChatAdapter.CutomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat,null);
        CutomViewHolder cutomViewHolder = new CutomViewHolder(view);
        return  cutomViewHolder;
    }

    @Override
    public void onBindViewHolder(ChatAdapter.CutomViewHolder holder, int position) {

        Message message = messageList.get(position);
        holder.message.setText(message.getMessage());
        holder.tvTime.setText(message.getTime());
        if(message.isMyMessage()){
            holder.message.setBackgroundResource(R.drawable.border_button_gray);
            holder.container.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            if(!myImage.isEmpty())
                Utils.setImage(context,myImage,holder.ivAvatar);
        }else {
            holder.message.setBackgroundResource(R.drawable.border_button_blue);
            holder.container.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);

            if(!userImage.isEmpty())
                Utils.setImage(context,userImage,holder.ivAvatar);
        }

    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }
    class CutomViewHolder extends RecyclerView.ViewHolder{
        TextView message,tvTime;
        RelativeLayout container;
        CircleImageView ivAvatar;
        public CutomViewHolder(View view){
            super(view);
            message = view.findViewById(R.id.tvt_msg);
            container=view.findViewById(R.id.container);
            tvTime=view.findViewById(R.id.tv_time);
            ivAvatar=view.findViewById(R.id.iv_avatar);
        }
    }
    public int addMessage(Message message){
        if(messageList == null)
            messageList = new ArrayList<>();
        messageList.add(message);
        notifyDataSetChanged();
        return messageList.size()-1;
    }
    public void removeMessage(int index){
        messageList.remove(index);
        notifyDataSetChanged();
    }
}