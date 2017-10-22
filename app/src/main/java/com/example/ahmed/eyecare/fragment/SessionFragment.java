package com.example.ahmed.eyecare.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmed.eyecare.R;
import com.example.ahmed.eyecare.adapter.SpeakerAdapter;
import com.example.ahmed.eyecare.api.utils.RetrofitRequest;
import com.example.ahmed.eyecare.api.utils.RetrofitResponse;
import com.example.ahmed.eyecare.model.Session;
import com.example.ahmed.eyecare.utils.Constant;
import com.example.ahmed.eyecare.utils.SharedPref;

/**
 * A simple {@link Fragment} subclass.
 */
public class SessionFragment extends Fragment {


    private Toolbar mToolbar;
    private TextView tvName;
    private TextView tvCleanTech;
    private LinearLayout linearContainer;
    private TextView tvDate;
    private TextView tvTime;
    private TextView tvLocation,tvAddToMyAgenda;
    private LinearLayout llAddToMyAgenda;
    private TextView tvSessionBody;
    private TextView speaker;
    private RecyclerView recycleview;
    private Session session;
    private ImageView ivAddToMyAgenda;

    public SessionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_session, container, false);
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
        session = (Session) getArguments().getSerializable(Constant.INTENT_SESSION_KEY);
        if(session!=null)
            setData();
    }

    private void setData() {
        tvName.setText(session.getSessionName());
        tvDate.setText(session.getDayDate()+" "+session.getMonth());
        tvTime.setText(session.getTime());
        tvSessionBody.setText(session.getDesc());
        SpeakerAdapter speakerAdapter = new SpeakerAdapter(session.getSpeaker(),getActivity(),R.color.blue);
        recycleview.setAdapter(speakerAdapter);
        if (session.isMyAgenda()) {
            ivAddToMyAgenda.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.calendarred));
            tvAddToMyAgenda.setText(getString(R.string.added_to_Your_agenda));
        }
    }

    private void findViewById(View view) {

        mToolbar = view.findViewById(R.id.toolbar);
        tvName = view.findViewById(R.id.tv_name);
        tvCleanTech = view.findViewById(R.id.tv_clean_tech);
        linearContainer = view.findViewById(R.id.linear_container);
        tvDate = view.findViewById(R.id.tv_date);
        tvTime = view.findViewById(R.id.tv_time);
        tvLocation = view.findViewById(R.id.tv_location);
        llAddToMyAgenda = view.findViewById(R.id.ll_add_to_my_agenda);
        tvAddToMyAgenda = view.findViewById(R.id.tv_add_to_my_agenda);
        ivAddToMyAgenda = view.findViewById(R.id.iv_add_to_my_agenda);
        tvSessionBody = view.findViewById(R.id.tv_session_body);
        speaker = view.findViewById(R.id.speaker);
        recycleview = view.findViewById(R.id.recycleview);
        recycleview.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
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
    private void onClick(){
        llAddToMyAgenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(session.isMyAgenda())
                    return;
                RetrofitRequest.addToMyAgenda(SharedPref.getMyAccount(getContext()).getUserId(), session.getId(), new RetrofitResponse<Boolean>() {
                    @Override
                    public void onSuccess(Boolean aBoolean) {
                        ivAddToMyAgenda.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.calendarred));
                        tvAddToMyAgenda.setText(getString(R.string.added_to_Your_agenda));
                    }

                    @Override
                    public void onFailed(String errorMessage) {
                        Toast.makeText(getContext(), R.string.session_added_error, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
