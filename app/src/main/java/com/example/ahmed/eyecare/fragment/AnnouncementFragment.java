package com.example.ahmed.eyecare.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ahmed.eyecare.R;
import com.example.ahmed.eyecare.api.utils.RetrofitRequest;
import com.example.ahmed.eyecare.api.utils.RetrofitResponse;
import com.example.ahmed.eyecare.model.Announcement;
import com.example.ahmed.eyecare.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AnnouncementFragment extends Fragment {


    private Toolbar mToolbar;
    private LinearLayout linearContainer;
    private ArrayList<String> announcementSelected;
    private ProgressBar progress,progressSend;
    List<Announcement> announcementList;
    CheckBox checkBox;
    Button btnSend;
    EditText etBody, etTitle;

    public AnnouncementFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_announcement, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViewById(view);
        onClick();
    }

    private void onClick() {
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etTitle.getText().toString().trim().isEmpty()) {
                    etTitle.setError(getString(R.string.insert_data));
                    return;
                }
                if(etBody.getText().toString().trim().isEmpty()) {
                    etBody.setError(getString(R.string.insert_data));
                    return;
                }
                HashMap<String, String> hashMap = new HashMap<>();
                for (String item : announcementSelected) {
                    hashMap.put("target_id",item);
                }
                sendNotofication(true);
                RetrofitRequest.addNotification(etTitle.getText().toString(), etBody.getText().toString(), hashMap, new RetrofitResponse<Boolean>() {
                    @Override
                    public void onSuccess(Boolean aBoolean) {
                        if(aBoolean){
                            sendNotofication(false);
                            Toast.makeText(getContext(), getString(R.string.notification​_sent​_successfully), Toast.LENGTH_SHORT).show();
                            getActivity().onBackPressed();
                        }
                    }

                    @Override
                    public void onFailed(String errorMessage) {
                        Toast.makeText(getContext(),errorMessage, Toast.LENGTH_SHORT).show();
                        sendNotofication(false);
                    }
                });
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        announcementSelected = new ArrayList<>();
        loadAnnouncementList();
    }

    private void findViewById(View view) {
        mToolbar = view.findViewById(R.id.toolbar);
        linearContainer = view.findViewById(R.id.linear_container);
        progress = view.findViewById(R.id.progress);
        btnSend = view.findViewById(R.id.btn_send);
        etTitle = view.findViewById(R.id.et_title);
        etBody = view.findViewById(R.id.et_body);
        progressSend=view.findViewById(R.id.progress_send);

        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Announcement");

        mToolbar.setNavigationIcon(R.drawable.ic_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
    }

    private void loadAnnouncementList() {
        RetrofitRequest.getAnnouncementList(new RetrofitResponse<List<Announcement>>() {
            @Override
            public void onSuccess(List<Announcement> announcements) {
                announcementList = announcements;
                for (final Announcement item : announcements) {
                    checkBox = new CheckBox(getContext());
                    checkBox.setText(item.getName());
                    checkBox.setTextSize(15);
                    checkBox.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.border_button));
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    params.setMargins(20, 20, 20, 20);
                    checkBox.setLayoutParams(params);
                    checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (isChecked)
                                announcementSelected.add(String.valueOf(item.getId()));
                            else
                                announcementSelected.remove(String.valueOf(item.getId()));
                        }
                    });
                    linearContainer.addView(checkBox);
                    progress.setVisibility(View.GONE);
                    btnSend.setEnabled(true);
                }
            }

            @Override
            public void onFailed(String errorMessage) {
                progress.setVisibility(View.GONE);
                Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void sendNotofication(boolean send){
        if(send) {
            progressSend.setVisibility(View.VISIBLE);
            btnSend.setEnabled(false);
        }else {
            progressSend.setVisibility(View.GONE);
            btnSend.setEnabled(true);
        }
    }
}
