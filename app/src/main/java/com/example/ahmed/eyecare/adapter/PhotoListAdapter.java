package com.example.ahmed.eyecare.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ahmed.eyecare.R;
import com.example.ahmed.eyecare.fragment.ShowPhotoFragment;
import com.example.ahmed.eyecare.fragment.SpeakerFragment;
import com.example.ahmed.eyecare.interfaces.OnClickListenerAdapter;
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
    FragmentActivity fragmentActivity;
    OnClickListenerAdapter onClickListenerAdapter;
    public PhotoListAdapter(FragmentActivity fragmentActivity, List<Photo> photos, Fragment fragment) {
        this.photos=photos;
        this.fragmentActivity=fragmentActivity;
        onClickListenerAdapter = (OnClickListenerAdapter) fragment;
    }

    @Override
    public PhotoListAdapter.CutomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo, null);
        CutomViewHolder cutomViewHolder = new CutomViewHolder(view);
        return cutomViewHolder;
    }

    @Override
    public void onBindViewHolder(PhotoListAdapter.CutomViewHolder holder, final int position) {

        final Photo photo = photos.get(position);
        Utils.setImage(fragmentActivity, photo.getImage(), holder.imageView);
        if(photo.isLiked()) {
            holder.ivLike.setColorFilter(ContextCompat.getColor(fragmentActivity, R.color.red_like));
        }else {
            holder.ivLike.setColorFilter(null);
        }
        holder.ivLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListenerAdapter.onClick(position);
            }
        });
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constant.INTENT_SHOW_PHOTO_KEY,photo);
                Utils.goToFragment(fragmentActivity, new ShowPhotoFragment(), "Back", bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    class CutomViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView,ivLike;
        public CutomViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.image);
            ivLike=view.findViewById(R.id.iv_like);
        }
    }
    public void addPhoto(Photo photo){
        this.photos.add(photo);
        notifyItemInserted(photos.size()-1);
    }
    public void updatePhoto(int position,Photo photo){
        this.photos.set(position,photo);
        notifyItemChanged(position);
    }
}
