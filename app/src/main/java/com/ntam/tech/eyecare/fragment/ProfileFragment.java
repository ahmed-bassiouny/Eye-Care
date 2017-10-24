package com.ntam.tech.eyecare.fragment;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ntam.tech.eyecare.R;
import com.ntam.tech.eyecare.api.modelResponse.AddPhotoResponse;
import com.ntam.tech.eyecare.api.utils.RetrofitRequest;
import com.ntam.tech.eyecare.api.utils.RetrofitResponse;
import com.ntam.tech.eyecare.utils.MyAccount;
import com.ntam.tech.eyecare.utils.SharedPref;
import com.ntam.tech.eyecare.utils.Utils;
import com.mvc.imagepicker.ImagePicker;

import java.io.ByteArrayOutputStream;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {


    private Toolbar mToolbar;
    ProgressBar progress;

    private ImageView ivAvatar;
    private TextView tvName;
    private TextView tvPosition;
    private TextView tvEditBio;
    //private TextView tvEditUserInfo;
    private EditText /*etEmail, etCountry, etMobile, etHospital,*/ etBio;
    private ImageView btnSelectImage;

    private boolean bioEditable = false;
    MyAccount myAccount;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
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
        myAccount =SharedPref.getMyAccount(getContext());
        setData();
    }

    private void onClick() {
        tvEditBio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editBio();
            }
        });
        btnSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.pickImage(ProfileFragment.this, "Select your image:");
            }
        });
    }

    private void findViewById(View view) {
        mToolbar = view.findViewById(R.id.toolbar);

        ivAvatar = view.findViewById(R.id.iv_avatar);
        tvName = view.findViewById(R.id.tv_name);
        tvPosition = view.findViewById(R.id.tv_position);
        tvEditBio = view.findViewById(R.id.tv_edit_bio);
        btnSelectImage = view.findViewById(R.id.btn_select_image);
        progress = view.findViewById(R.id.progress);
        //tvEditUserInfo = view.findViewById(R.id.tv_edit_user_info);
        /*
        etEmail = view.findViewById(R.id.et_email);
        etMobile = view.findViewById(R.id.ed_mobile);
        etCountry = view.findViewById(R.id.et_country);
        etHospital = view.findViewById(R.id.et_hospital);*/
        etBio = view.findViewById(R.id.et_bio);

        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Profile");


        mToolbar.setNavigationIcon(R.drawable.ic_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
    }

    private void setData(){
        etBio.setText(myAccount.getBio());
        tvName.setText(myAccount.getUserName());
        tvPosition.setText(myAccount.getPosition());
        if(!myAccount.getUserImage().isEmpty())
            Utils.setImage(getContext(),myAccount.getUserImage(),ivAvatar);
    }
    private void editBio() {
        if (bioEditable) {
            if(etBio.getText().toString().trim().isEmpty())
                return;
            RetrofitRequest.updateBio(myAccount.getUserId(), etBio.getText().toString(), new RetrofitResponse<Boolean>() {
                @Override
                public void onSuccess(Boolean aBoolean) {
                    if(aBoolean){
                        etBio.setEnabled(false);
                        tvEditBio.setText(getString(R.string.edit));
                        bioEditable=false;
                        SharedPref.setBio(getContext(),etBio.getText().toString());
                        Toast.makeText(getContext(), getString(R.string.edit_successfully), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailed(String errorMessage) {

                }
            });
        }else {
            etBio.setEnabled(true);
            tvEditBio.setText(getString(R.string.save));
            bioEditable=true;
        }
    }
    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK) {
            startUpload(true);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Bitmap bitmap = ImagePicker.getImageFromResult(getContext(), requestCode, resultCode, data);
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    final String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
                    uploadImage(encoded);
                }
            }).start();
        }
    }

    private void startUpload(boolean start) {
        if (start) {
            progress.setVisibility(View.VISIBLE);
            btnSelectImage.setVisibility(View.GONE);
        } else {
            progress.setVisibility(View.GONE);
            btnSelectImage.setVisibility(View.VISIBLE);
        }
    }

    private void uploadImage(String imagePathEncode) {
        RetrofitRequest.editUserImage(myAccount.getUserId(), imagePathEncode, new RetrofitResponse<AddPhotoResponse>() {
            @Override
            public void onSuccess(AddPhotoResponse addPhotoResponse) {
                startUpload(false);
                if(addPhotoResponse.getStatus()){
                    SharedPref.setUserImage(getContext(),addPhotoResponse.getImage());
                    Utils.setImage(getContext(),addPhotoResponse.getImage(),ivAvatar);

                }else {
                    Toast.makeText(getContext(), addPhotoResponse.getMassage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailed(String errorMessage) {
                Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
                startUpload(false);
            }
        });
    }
}

