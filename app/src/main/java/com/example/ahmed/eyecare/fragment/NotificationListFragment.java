package com.example.ahmed.eyecare.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ahmed.eyecare.R;
import com.example.ahmed.eyecare.adapter.NotificationAdapter;
import com.example.ahmed.eyecare.api.utils.RetrofitRequest;
import com.example.ahmed.eyecare.api.utils.RetrofitResponse;
import com.example.ahmed.eyecare.model.UserNotification;
import com.example.ahmed.eyecare.utils.SharedPref;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationListFragment extends Fragment {

    private Toolbar mToolbar;
    RecyclerView recycleview;
    ProgressBar progress;
    NotificationAdapter notificationAdapter;

    public NotificationListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notification_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViewById(view);
        loadData();
    }

    private void loadData() {
        RetrofitRequest.getListNotification(SharedPref.getMyAccount(getContext()).getUserId(), new RetrofitResponse<List<UserNotification>>() {
            @Override
            public void onSuccess(List<UserNotification> userNotifications) {
                notificationAdapter = new NotificationAdapter(userNotifications);
                recycleview.setAdapter(notificationAdapter);
                progress.setVisibility(View.GONE);
            }

            @Override
            public void onFailed(String errorMessage) {
                Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
                progress.setVisibility(View.GONE);
            }
        });
    }

    private void findViewById(View view) {
        mToolbar = view.findViewById(R.id.toolbar);
        recycleview = view.findViewById(R.id.recycleview);
        progress = view.findViewById(R.id.progress);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Announcements");

        mToolbar.setNavigationIcon(R.drawable.ic_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        recycleview.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

    }

}
