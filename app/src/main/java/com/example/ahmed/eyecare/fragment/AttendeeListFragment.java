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
import com.example.ahmed.eyecare.adapter.AttendeeAdapter;
import com.example.ahmed.eyecare.api.utils.RetrofitRequest;
import com.example.ahmed.eyecare.api.utils.RetrofitResponse;
import com.example.ahmed.eyecare.model.AttendeLisWithLetter;
import com.example.ahmed.eyecare.model.Attendee;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AttendeeListFragment extends Fragment {

    private RecyclerView recycleview;
    private Toolbar mToolbar;
    private ProgressBar progress;
    private AttendeeAdapter attendeeAdapter;
    private List<Attendee> attendeeList = new ArrayList<>();;


    public AttendeeListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_attendee_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViewById(view);
        loadData(1);
    }

    private void loadData(int pageNumber) {
        progress.setVisibility(View.VISIBLE);
        recycleview.setVisibility(View.GONE);
        RetrofitRequest.getAllAttendee(pageNumber, new RetrofitResponse<List<AttendeLisWithLetter>>() {
            @Override
            public void onSuccess(final List<AttendeLisWithLetter> attendeLisWithLetters) {
                if (attendeLisWithLetters == null)
                    onFailed(getString(R.string.cant_load_data));
                else {
                    // loop to get attendees
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            for (AttendeLisWithLetter item : attendeLisWithLetters) {
                                for (Attendee attendee : item.getAttendees())
                                    attendeeList.add(attendee);
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        attendeeAdapter = new AttendeeAdapter(attendeeList, getContext());
                                        recycleview.setAdapter(attendeeAdapter);
                                        progress.setVisibility(View.GONE);
                                        recycleview.setVisibility(View.VISIBLE);
                                    }
                                });
                            }

                        }
                    }).start();
                }
            }

            @Override
            public void onFailed(String errorMessage) {
                progress.setVisibility(View.GONE);
                Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void findViewById(View view) {

        recycleview = view.findViewById(R.id.recycleview);
        mToolbar = view.findViewById(R.id.toolbar);
        progress = view.findViewById(R.id.progress);
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
}
