package com.example.ahmed.eyecare.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmed.eyecare.R;
import com.example.ahmed.eyecare.api.utils.RetrofitRequest;
import com.example.ahmed.eyecare.api.utils.RetrofitResponse;
import com.example.ahmed.eyecare.utils.MyAccount;
import com.example.ahmed.eyecare.utils.SharedPref;
import com.example.ahmed.eyecare.utils.Utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {


    private Toolbar mToolbar;

    private ImageView ivAvatar;
    private TextView tvName;
    private TextView tvPosition;
    private TextView tvEditBio;
    //private TextView tvEditUserInfo;
    private EditText /*etEmail, etCountry, etMobile, etHospital,*/ etBio;

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
    }

    private void findViewById(View view) {
        mToolbar = view.findViewById(R.id.toolbar);

        ivAvatar = view.findViewById(R.id.iv_avatar);
        tvName = view.findViewById(R.id.tv_name);
        tvPosition = view.findViewById(R.id.tv_position);
        tvEditBio = view.findViewById(R.id.tv_edit_bio);
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
}

