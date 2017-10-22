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
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmed.eyecare.R;
import com.example.ahmed.eyecare.adapter.SessionSpeakerAdapter;
import com.example.ahmed.eyecare.model.Session;
import com.example.ahmed.eyecare.model.Speaker;
import com.example.ahmed.eyecare.utils.Constant;
import com.example.ahmed.eyecare.utils.Utils;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class SpeakerFragment extends Fragment {


    private CircleImageView ivAvatar;
    private TextView tvName;
    private TextView tvPosition;
    private TextView tvCompany;
    private TextView tvBio;
    private RecyclerView recycleview;
    private Toolbar mToolbar;

    Speaker speaker;
    SessionSpeakerAdapter sessionSpeakerAdapter;

    public SpeakerFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        speaker = (Speaker) getArguments().getSerializable(Constant.INTENT_OPEN_SPEAKER_KEY);
        if (speaker == null) {
            Toast.makeText(getActivity(), R.string.cant_load_data, Toast.LENGTH_SHORT).show();
            getActivity().onBackPressed();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_speaker, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViewById(view);
        setData();
    }

    private void setData() {
        tvName.setText(speaker.getName());
        tvPosition.setText(speaker.getPosition());
        tvCompany.setText(speaker.getCompany());
        tvBio.setText(speaker.getBio());
        if (!speaker.getImage().isEmpty())
            Utils.setImage(getContext(), speaker.getImage(), ivAvatar);
        sessionSpeakerAdapter = new SessionSpeakerAdapter(speaker.getSession(), getActivity());
        recycleview.setAdapter(sessionSpeakerAdapter);

    }

    private void findViewById(View view) {
        ivAvatar = view.findViewById(R.id.iv_avatar);
        tvName = view.findViewById(R.id.tv_name);
        tvPosition = view.findViewById(R.id.tv_position);
        tvCompany = view.findViewById(R.id.tv_company);
        tvBio = view.findViewById(R.id.tv_bio);
        recycleview = view.findViewById(R.id.recycleview);
        recycleview.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recycleview.setNestedScrollingEnabled(false);
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

    @Override
    public void onResume() {
        super.onResume();
        if(SessionFragment.sessionId>0){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    List<Session> sessionList = speaker.getSession();
                    int size =sessionList.size();
                    for(int i=0;i<size;i++){
                        final Session session=sessionList.get(i);
                        if(session.getId()==SessionFragment.sessionId){
                            session.setisMyAgenda(true);
                            sessionList.set(i,session);
                            speaker.setSessions(sessionList);
                            final int finalI = i;
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    sessionSpeakerAdapter.updateSession(finalI,session);
                                }
                            });
                            break;
                        }
                    }
                }
            }).start();
        }
    }
}
