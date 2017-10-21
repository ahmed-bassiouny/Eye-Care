package com.example.ahmed.eyecare.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ahmed.eyecare.R;
import com.example.ahmed.eyecare.api.utils.RetrofitRequest;
import com.example.ahmed.eyecare.api.utils.RetrofitResponse;
import com.example.ahmed.eyecare.model.Photo;
import com.example.ahmed.eyecare.utils.Constant;
import com.example.ahmed.eyecare.utils.SharedPref;
import com.example.ahmed.eyecare.utils.Utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShowPhotoFragment extends Fragment {

    private Toolbar mToolbar;
    ImageView imageView;
    TextView tvLike,tvComment,tvShare;
    Photo photo;
    int userId;
    public ShowPhotoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userId = SharedPref.getMyAccount(getContext()).getUserId();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_photo, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViewById(view);
        onClick();
    }

    @Override
    public void onStart() {
        super.onStart();
        photo= (Photo) getArguments().getSerializable(Constant.INTENT_SHOW_PHOTO_KEY);
        if(photo==null)
            getActivity().onBackPressed();
        Utils.setImage(getContext(),photo.getImage(),imageView);
        setLikeColor();
    }

    private void onClick() {
        tvLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                photo.changeLike();
                setLikeColor();
                RetrofitRequest.addLikeToPhoto(userId, photo.getId(), new RetrofitResponse<Boolean>() {
                    @Override
                    public void onSuccess(Boolean aBoolean) {
                        if(!aBoolean) {
                            onFailed("");
                        }
                    }

                    @Override
                    public void onFailed(String errorMessage) {
                        photo.changeLike();
                        setLikeColor();
                    }
                });
            }
        });
    }

    private void findViewById(View view) {
        imageView=view.findViewById(R.id.img);
        tvLike=view.findViewById(R.id.tv_like);
        tvComment=view.findViewById(R.id.tv_comment);
        tvShare=view.findViewById(R.id.tv_share);
        mToolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);

        mToolbar.setNavigationIcon(R.drawable.ic_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
    }
    private void setLikeColor(){
        if(photo.isLiked()) {
            tvLike.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(getContext(),R.drawable.ic_thump_up_red),null,null,null);
            tvLike.setTextColor(ContextCompat.getColor(getContext(),R.color.red_like));
        }else {
            tvLike.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(getContext(),R.drawable.ic_thump_up),null,null,null);
            tvLike.setTextColor(ContextCompat.getColor(getContext(),R.color.white));
        }
    }
}
