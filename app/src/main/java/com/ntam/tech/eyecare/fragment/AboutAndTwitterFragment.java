package com.ntam.tech.eyecare.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.ntam.tech.eyecare.R;
import com.ntam.tech.eyecare.api.utils.RetrofitRequest;
import com.ntam.tech.eyecare.api.utils.RetrofitResponse;
import com.ntam.tech.eyecare.model.About;
import com.ntam.tech.eyecare.utils.Constant;
import com.ntam.tech.eyecare.utils.Utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutAndTwitterFragment extends Fragment {


    WebView webView;
    ImageView imageView;
    public static final int ABOUT_PAGE=1;
    public static final int TWITTER_PAGE=2;
    private int currentPage;
    public AboutAndTwitterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_twitter, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        webView = view.findViewById(R.id.webview);
        imageView =view.findViewById(R.id.image_view);
        webView.setWebViewClient(new WebViewClient());
        currentPage=getArguments().getInt(Constant.INTENT_TWITTER_ABOUT_KEY);
        loadData();
    }

    private void loadData(){
        RetrofitRequest.getAbout(new RetrofitResponse<About>() {
            @Override
            public void onSuccess(About about) {
                if(currentPage==TWITTER_PAGE){
                    imageView.setVisibility(View.GONE);
                    webView.loadUrl(Utils.getTwitterUrl(about.getTag()));
                }else if(currentPage==ABOUT_PAGE){
                    webView.loadDataWithBaseURL("", about.getContent(), "text/html", "utf-8", "");
                    Utils.setImage(getContext(),about.getImage(),imageView);
                }else {

                }
            }

            @Override
            public void onFailed(String errorMessage) {

            }
        });
    }
}
