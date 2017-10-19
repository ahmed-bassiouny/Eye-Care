package com.example.ahmed.eyecare.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ahmed.eyecare.R;
import com.example.ahmed.eyecare.fragment.SpeakerFragment;
import com.example.ahmed.eyecare.model.Photo;
import com.example.ahmed.eyecare.model.Speaker;
import com.example.ahmed.eyecare.utils.Constant;
import com.example.ahmed.eyecare.utils.Utils;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ahmed on 11/10/17.
 */

public class PhotoListAdapter extends RecyclerView.Adapter<PhotoListAdapter.CutomViewHolder> {


    List<Photo> photos;
    Context context;
    public PhotoListAdapter(Context context,List<Photo> photos) {
        this.photos=photos;
        this.context=context;
    }

    @Override
    public PhotoListAdapter.CutomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo, null);
        CutomViewHolder cutomViewHolder = new CutomViewHolder(view);
        return cutomViewHolder;
    }

    @Override
    public void onBindViewHolder(PhotoListAdapter.CutomViewHolder holder, int position) {

        final Photo photo = photos.get(position);
        Utils.setImage(context, photo.getImage(), holder.imageView);
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    class CutomViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public CutomViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.image);
        }
    }
    public void addPhoto(Photo photo){
        this.photos.add(photo);
        notifyDataSetChanged();
    }
}
