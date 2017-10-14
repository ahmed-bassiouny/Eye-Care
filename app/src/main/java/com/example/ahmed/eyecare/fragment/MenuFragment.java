package com.example.ahmed.eyecare.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ahmed.eyecare.R;
import com.example.ahmed.eyecare.utils.Utils;

public class MenuFragment extends Fragment implements View.OnClickListener {

    private TextView tvSpeeker;
    private TextView tvAttendee;
    private TextView tvAgenda;
    private ImageView ivSpeeker;
    private ImageView ivAttendee;
    private ImageView ivAgenda;
    private TextView tvLogistics;
    private TextView tvNewfeed;
    private TextView tvAnnouncement;
    private ImageView ivLogistics;
    private ImageView ivNewfeed;
    private ImageView ivAnnouncement;
    private TextView tvLiveVote;
    private TextView tvPhoto;
    private TextView tvTwitter;
    private ImageView ivLiveVote;
    private ImageView ivPhoto;
    private ImageView ivTwitter;
    private TextView tvMessage;
    private TextView tvSetting;
    private TextView tvAbout;
    private ImageView ivMessage;
    private ImageView ivSetting;
    private ImageView ivAbout;
    private TextView tvAdmin;
    private ImageView ivAdmin;
    private Toolbar mToolbar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViewById(view);
        onClick();
        setToolbar();
    }

    private void setToolbar() {
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

    private void onClick() {
        tvSpeeker.setOnClickListener(this);
        tvAttendee.setOnClickListener(this);
        tvAgenda.setOnClickListener(this);
        ivSpeeker.setOnClickListener(this);
        ivAttendee.setOnClickListener(this);
        ivAgenda.setOnClickListener(this);
        tvLogistics.setOnClickListener(this);
        tvNewfeed.setOnClickListener(this);
        tvAnnouncement.setOnClickListener(this);
        ivLogistics.setOnClickListener(this);
        ivNewfeed.setOnClickListener(this);
        ivAnnouncement.setOnClickListener(this);
        tvLiveVote.setOnClickListener(this);
        tvPhoto.setOnClickListener(this);
        tvTwitter.setOnClickListener(this);
        ivLiveVote.setOnClickListener(this);
        ivPhoto.setOnClickListener(this);
        ivTwitter.setOnClickListener(this);
        tvMessage.setOnClickListener(this);
        tvSetting.setOnClickListener(this);
        tvAbout.setOnClickListener(this);
        ivMessage.setOnClickListener(this);
        ivSetting.setOnClickListener(this);
        ivAbout.setOnClickListener(this);
        tvAdmin.setOnClickListener(this);
        ivAdmin.setOnClickListener(this);
    }

    private void findViewById(View view) {
        tvSpeeker = view.findViewById(R.id.tv_speeker);
        tvAttendee = view.findViewById(R.id.tv_attendee);
        tvAgenda = view.findViewById(R.id.tv_agenda);
        ivSpeeker = view.findViewById(R.id.iv_speeker);
        ivAttendee = view.findViewById(R.id.iv_attendee);
        ivAgenda = view.findViewById(R.id.iv_agenda);
        tvLogistics = view.findViewById(R.id.tv_logistics);
        tvNewfeed = view.findViewById(R.id.tv_newfeed);
        tvAnnouncement = view.findViewById(R.id.tv_announcement);
        ivLogistics = view.findViewById(R.id.iv_logistics);
        ivNewfeed = view.findViewById(R.id.iv_newfeed);
        ivAnnouncement = view.findViewById(R.id.iv_announcement);
        tvLiveVote = view.findViewById(R.id.tv_live_vote);
        tvPhoto = view.findViewById(R.id.tv_photo);
        tvTwitter = view.findViewById(R.id.tv_twitter);
        ivLiveVote = view.findViewById(R.id.iv_live_vote);
        ivPhoto = view.findViewById(R.id.iv_photo);
        ivTwitter = view.findViewById(R.id.iv_twitter);
        tvMessage = view.findViewById(R.id.tv_message);
        tvSetting = view.findViewById(R.id.tv_setting);
        tvAbout = view.findViewById(R.id.tv_about);
        ivMessage = view.findViewById(R.id.iv_message);
        ivSetting = view.findViewById(R.id.iv_setting);
        ivAbout = view.findViewById(R.id.iv_about);
        tvAdmin = view.findViewById(R.id.tv_admin);
        ivAdmin = view.findViewById(R.id.iv_admin);
        mToolbar = view.findViewById(R.id.toolbar);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_speeker:
            case R.id.iv_speeker:
                Utils.goToFragment(getActivity(),new SpeakerListFragment(),"Back",null);
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
