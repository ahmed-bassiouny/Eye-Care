package com.example.ahmed.eyecare.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.ahmed.eyecare.R;
import com.example.ahmed.eyecare.adapter.PhotoAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFeed extends Fragment {

    ViewPager pager;
    PagerAdapter adapter;
    TabLayout tabLayout;
    ImageView ivWritePost,ivChecIn;
    RecyclerView recycleview;

    public NewsFeed() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_news_feed, container, false);
        findViewById(view);
        onClick();
        setviewpager();
        return view;
    }
    private void findViewById(View view){
        pager =  view.findViewById(R.id.photos_viewpager);
        tabLayout = view.findViewById(R.id.tablayout);
        ivWritePost =view.findViewById(R.id.iv_write_post);
        ivChecIn = view.findViewById(R.id.iv_checkin);
        recycleview = view.findViewById(R.id.recycleview);

    }
    private void onClick(){

    }
    private void setviewpager(){

        adapter = new PhotoAdapter(getActivity());
        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager, true);
    }

}
