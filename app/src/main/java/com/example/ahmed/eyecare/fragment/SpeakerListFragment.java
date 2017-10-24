package com.example.ahmed.eyecare.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.*;
import android.view.Menu;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmed.eyecare.R;
import com.example.ahmed.eyecare.adapter.SpeakerAdapter;
import com.example.ahmed.eyecare.api.utils.RetrofitRequest;
import com.example.ahmed.eyecare.api.utils.RetrofitResponse;
import com.example.ahmed.eyecare.model.Speaker;
import com.example.ahmed.eyecare.utils.DummyData;
import com.example.ahmed.eyecare.utils.SharedPref;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SpeakerListFragment extends Fragment {


    private Toolbar mToolbar;
    private MenuItem mSearchAction;
    private boolean isSearchOpened = false;
    private EditText edtSeach;

    RecyclerView recycleview;
    ProgressBar progress;
    SpeakerAdapter speakerAdapter;

    List<Speaker> speakerList;
    List<Speaker> speakerListFilter;


    public SpeakerListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return  inflater.inflate(R.layout.fragment_speaker_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViewById(view);
        loadData();
    }

    private void findViewById(View view) {
        mToolbar = view.findViewById(R.id.toolbar);
        recycleview = view.findViewById(R.id.recycleview);
        progress = view.findViewById(R.id.progress);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Speakers");

        mToolbar.setNavigationIcon(R.drawable.ic_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        mSearchAction = menu.findItem(R.id.action_search);
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_search:
                handleMenuSearch();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void handleMenuSearch() {
        ActionBar action = ((AppCompatActivity) getActivity()).getSupportActionBar(); //get the actionbar

        if (isSearchOpened) { //test if the search is open

            action.setDisplayShowCustomEnabled(false); //disable a custom view inside the actionbar
            action.setDisplayShowTitleEnabled(true); //show the title in the action bar

            //hides the keyboard
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(edtSeach.getWindowToken(), 0);

            //add the search icon in the action bar
            mSearchAction.setIcon(getResources().getDrawable(R.drawable.ic_search));

            isSearchOpened = false;
        } else { //open the search entry

            action.setDisplayShowCustomEnabled(true); //enable it to display a
            // custom view in the action bar.
            action.setCustomView(R.layout.search_bar);//add the custom view
            action.setDisplayShowTitleEnabled(false); //hide the title

            edtSeach = (EditText) action.getCustomView().findViewById(R.id.edtSearch); //the text editor

            //this is a listener to do a search when the user clicks on search button
            edtSeach.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                        //doSearch();
                        return true;
                    }
                    return false;
                }
            });
            edtSeach.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    if (s.toString().trim().isEmpty()) {
                        speakerAdapter.updateList(speakerList);
                    }
                    else if (speakerList != null &&s.toString().trim().length() == 1 || before == 1) {
                        searchInSpeaker(s.toString());
                    }
                    else if(speakerListFilter != null) {
                        searchInFilter(s.toString());
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });


            edtSeach.requestFocus();

            //open the keyboard focused in the edtSearch
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(edtSeach, InputMethodManager.SHOW_IMPLICIT);


            //add the close icon
            mSearchAction.setIcon(getResources().getDrawable(R.drawable.ic_close));

            isSearchOpened = true;
        }
    }

    private void searchInSpeaker(final String text){
        final ArrayList<Speaker> filter = new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(Speaker item: speakerList){
                    if(item.getName().toLowerCase().contains(text.toLowerCase())){
                        filter.add(item);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                speakerListFilter=filter;
                                speakerAdapter.updateList(speakerListFilter);
                            }
                        });
                    }
                }
            }
        }).start();
    }
    private void searchInFilter(final String text){
        final ArrayList<Speaker> filter = new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(Speaker item: speakerListFilter){
                    if(item.getName().toLowerCase().contains(text.toLowerCase())){
                        filter.add(item);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                speakerListFilter=filter;
                                speakerAdapter.updateList(speakerListFilter);
                            }
                        });
                    }
                }
            }
        }).start();
        if(filter.size()==0){
            speakerListFilter=filter;
            speakerAdapter.updateList(filter);
        }
    }
    private void loadData() {
        RetrofitRequest.getAllSpeaker(SharedPref.getMyAccount(getContext()).getUserId(), new RetrofitResponse<List<com.example.ahmed.eyecare.model.Speaker>>() {
            @Override
            public void onSuccess(List<com.example.ahmed.eyecare.model.Speaker> speakers) {
                speakerList = speakers;
                speakerListFilter = speakers;
                speakerAdapter = new SpeakerAdapter(speakers, getActivity(),R.color.white);
                recycleview.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                recycleview.setAdapter(speakerAdapter);
                progress.setVisibility(View.GONE);
            }

            @Override
            public void onFailed(String errorMessage) {
                Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
                progress.setVisibility(View.GONE);
            }
        });
    }
}
