package com.example.ahmed.eyecare.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ahmed.eyecare.R;
import com.example.ahmed.eyecare.activity.MenuContainer;
import com.example.ahmed.eyecare.adapter.NewsFeedAdapter;
import com.example.ahmed.eyecare.adapter.PhotoAdapter;
import com.example.ahmed.eyecare.api.utils.RetrofitRequest;
import com.example.ahmed.eyecare.api.utils.RetrofitResponse;
import com.example.ahmed.eyecare.dialog.AddPostDialog;
import com.example.ahmed.eyecare.interfaces.OnClickPostAdapter;
import com.example.ahmed.eyecare.model.Post;
import com.example.ahmed.eyecare.utils.Constant;
import com.example.ahmed.eyecare.utils.SharedPref;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFeedFragment extends Fragment implements OnClickPostAdapter {

    // constant
    public static final int NEW_POST = 123;
    ViewPager pager;
    PagerAdapter adapter;
    TabLayout tabLayout;
    ImageView ivWritePost, ivChecIn, ivMenu;
    RecyclerView recycleview;
    NewsFeedAdapter newsFeedAdapter;
    int pageNumber = 1;
    List<Post> postList;
    ProgressBar progress;
    int userId;

    public NewsFeedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_news_feed, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViewById(view);
        onClick();
        setviewpager();
    }

    @Override
    public void onStart() {
        super.onStart();
        userId = SharedPref.getMyAccount(getContext()).getUserId();
        loadData();
    }


    private void findViewById(View view) {
        pager = view.findViewById(R.id.photos_viewpager);
        tabLayout = view.findViewById(R.id.tablayout);
        ivWritePost = view.findViewById(R.id.iv_write_post);
        ivChecIn = view.findViewById(R.id.iv_checkin);
        recycleview = view.findViewById(R.id.recycleview);
        ivMenu = view.findViewById(R.id.iv_menu);
        progress = view.findViewById(R.id.progress);
        recycleview.setNestedScrollingEnabled(false);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recycleview.setLayoutManager(linearLayoutManager);
        recycleview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int itemno = linearLayoutManager.findLastVisibleItemPosition();
                if (Constant.COUNT_ITEMS_PER_REQUEST - 1 == itemno) {
                    if ((postList.size() / Constant.COUNT_ITEMS_PER_REQUEST) == pageNumber) {
                        pageNumber++;
                        loadData();
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

            }
        });

    }

    private void onClick() {
        ivWritePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO open fragment dialog to send post
                startActivityForResult(new Intent(getContext(), AddPostDialog.class), NEW_POST);
            }
        });

        ivChecIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RetrofitRequest.checkIn(SharedPref.getMyAccount(getContext()).getUserId(), new RetrofitResponse<Post>() {
                    @Override
                    public void onSuccess(Post post) {
                        newsFeedAdapter.addPost(post);
                        Toast.makeText(getActivity(), R.string.check_in, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailed(String errorMessage) {
                        Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        ivMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MenuContainer.class));

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK && requestCode == NEW_POST) {
            Post post = (Post) data.getSerializableExtra(Constant.INTENT_POST_KEY);
            if (post == null)
                loadData();
            else
                newsFeedAdapter.addPost(post);
        }
    }

    private void setviewpager() {
        adapter = new PhotoAdapter(getActivity());
        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager, true);
    }

    private void loadData() {
        progress.setVisibility(View.VISIBLE);
        RetrofitRequest.getPosts(SharedPref.getMyAccount(getContext()).getUserId(), pageNumber, new RetrofitResponse<List<Post>>() {
            @Override
            public void onSuccess(List<Post> posts) {
                postList = posts;
                if (pageNumber == Constant.PAGE_NUMBER) {
                    newsFeedAdapter = new NewsFeedAdapter(posts, getContext(),NewsFeedFragment.this);
                    recycleview.setAdapter(newsFeedAdapter);
                } else {
                    newsFeedAdapter.updateAttendee(postList);
                }
                progress.setVisibility(View.GONE);
            }

            @Override
            public void onFailed(String errorMessage) {
                Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
                progress.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void likePost(final int position) {
        final Post post = postList.get(position);
        if (post.getPostId() == 0)
            return;
        post.setLike();
        postList.set(position, post);
        newsFeedAdapter.updatePost(position,post);
        RetrofitRequest.addLikeToPost(userId, post.getPostId(), new RetrofitResponse<Boolean>() {
            @Override
            public void onSuccess(Boolean aBoolean) {

            }

            @Override
            public void onFailed(String errorMessage) {
                post.setLike();
                postList.set(position, post);
                newsFeedAdapter.updatePost(position,post);
            }
        });
    }

    @Override
    public void commentPost(int position) {

    }
}
