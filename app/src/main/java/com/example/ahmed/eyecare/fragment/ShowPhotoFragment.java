package com.example.ahmed.eyecare.fragment;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import android.Manifest;
import android.widget.Toast;

import com.artjimlop.altex.AltexImageDownloader;
import com.example.ahmed.eyecare.R;
import com.example.ahmed.eyecare.api.utils.RetrofitRequest;
import com.example.ahmed.eyecare.api.utils.RetrofitResponse;
import com.example.ahmed.eyecare.model.Photo;
import com.example.ahmed.eyecare.utils.Constant;
import com.example.ahmed.eyecare.utils.SharedPref;
import com.example.ahmed.eyecare.utils.Utils;

import java.io.Serializable;

public class ShowPhotoFragment extends Fragment {

    private static final int WRITEEXTERNALSTORAGE =123 ;
    private Toolbar mToolbar;
    ImageView imageView;
    TextView tvLike, tvComment, tvShare,tvSave;
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
        photo = (Photo) getArguments().getSerializable(Constant.INTENT_SHOW_PHOTO_KEY);
        if (photo == null)
            getActivity().onBackPressed();
        Utils.setImage(getContext(), photo.getImage(), imageView);
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
                        if (!aBoolean) {
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
        tvComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constant.INTENT_SHOW_COMMENT_KEY, (Serializable) photo.getComments());
                bundle.putInt(Constant.INTENT_ITEM_ID_TYPE,photo.getId());
                bundle.putInt(Constant.INTENT_COMMENT_TYPE,CommentFragment.COMMENT_PHOTO);
                Utils.goToFragment(getActivity(),new CommentFragment(),"Back",bundle);
            }
        });
        tvShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareTextUrl();
            }
        });
        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isNetworkAvailable(getContext())) {
                    if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITEEXTERNALSTORAGE);
                    }else {
                        downloadImage();
                    }

                }else {
                    Toast.makeText(getContext(), "Check Your Connection", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode ==WRITEEXTERNALSTORAGE && (grantResults.length > 0) && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            downloadImage();
        }
    }

    private void findViewById(View view) {
        imageView = view.findViewById(R.id.img);
        tvLike = view.findViewById(R.id.tv_like);
        tvComment = view.findViewById(R.id.tv_comment);
        tvShare = view.findViewById(R.id.tv_share);
        tvSave = view.findViewById(R.id.tv_save);
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

    private void setLikeColor() {
        if (photo.isLiked()) {
            tvLike.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(getContext(), R.drawable.ic_thump_up_red), null, null, null);
            tvLike.setTextColor(ContextCompat.getColor(getContext(), R.color.red_like));
        } else {
            tvLike.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(getContext(), R.drawable.ic_thump_up), null, null, null);
            tvLike.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
        }
    }

    private void shareTextUrl() {

        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_SUBJECT, "Share Image");
        i.putExtra(Intent.EXTRA_SUBJECT, "Sharing Image");
        i.putExtra(Intent.EXTRA_TEXT, photo.getImage());
        startActivity(Intent.createChooser(i, "Share Image"));
    }

    private void downloadImage() {
        AltexImageDownloader.writeToDisk(getContext(),photo.getImage(),"");
        Toast.makeText(getContext(), "Downloading", Toast.LENGTH_SHORT).show();
    }
}
