package com.example.ahmed.eyecare.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ahmed.eyecare.R;

public class Menu extends Fragment implements View.OnClickListener {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        return view;
    }

    private void onCilick(View view) {
        view.findViewById(R.id.tv_speeker).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_speeker:
            case R.id.iv_speeker:
                break;

            case R.id.tv_attendee:
            case R.id.iv_attendee:
                break;

            case R.id.tv_agenda:
            case R.id.iv_agenda:
                break;

            case R.id.tv_logistics:
            case R.id.iv_logistics:
                break;

            case R.id.tv_newfeed:
            case R.id.iv_newfeed:
                break;

            case R.id.tv_announcement:
            case R.id.iv_announcement:
                break;

            case R.id.tv_live_vote:
            case R.id.iv_live_vote:
                break;

            case R.id.tv_photo:
            case R.id.iv_photo:
                break;

            case R.id.tv_twitter:
            case R.id.iv_twitter:
                break;

            case R.id.tv_message:
            case R.id.iv_message:
                break;
            case R.id.tv_setting:
            case R.id.iv_setting:
                break;
            case R.id.tv_about:
            case R.id.iv_about:
                break;
            case R.id.tv_admin:
            case R.id.iv_admin:
                break;

        }
    }
}
