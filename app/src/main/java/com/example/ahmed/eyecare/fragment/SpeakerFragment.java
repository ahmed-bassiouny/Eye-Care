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

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SpeakerFragment extends Fragment {


    private Toolbar mToolbar;
    private MenuItem mSearchAction;
    private boolean isSearchOpened = false;
    private EditText edtSeach;

    RecyclerView recycleview;
    SpeakerAdapter speakerAdapter;
    ProgressBar progress;
    List<Speaker> speakerList;
    List<Speaker> speakerListFilter;


    public SpeakerFragment() {
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
        View view = inflater.inflate(R.layout.fragment_speaker, container, false);
        findViewById(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadData();
    }

    private void findViewById(View view) {
        mToolbar = view.findViewById(R.id.toolbar);
        recycleview = view.findViewById(R.id.recycleview);
        progress = view.findViewById(R.id.progress);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
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
                    if (speakerList == null || speakerListFilter == null)
                        return;
                    Toast.makeText(getActivity(), "search in all", Toast.LENGTH_SHORT).show();

//                    if (s.toString().trim().isEmpty())
//                        Toast.makeText(getActivity(), "set data", Toast.LENGTH_SHORT).show();
//                    else if (s.toString().trim().length() == 1 || before == 1)
//                        Toast.makeText(getActivity(), "search in all", Toast.LENGTH_SHORT).show();
//                    else
//                        Toast.makeText(getActivity(), "search in all filter", Toast.LENGTH_SHORT).show();
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

    /*void filter(String text){
        List<DataHolder> temp = new ArrayList();
        for(DataHolder d: displayedList){
            //or use .equal(text) with you want equal match
            //use .toLowerCase() for better matches
            if(d.getEnglish().contains(text)){
                temp.add(d);
            }
        }
        //update recyclerview
        //disp_adapter.updateList(temp);
    }*/
    private void loadData() {
        RetrofitRequest.getAllSpeaker(DummyData.userID, new RetrofitResponse<List<com.example.ahmed.eyecare.model.Speaker>>() {
            @Override
            public void onSuccess(List<com.example.ahmed.eyecare.model.Speaker> speakers) {
                speakerList = speakers;
                speakerListFilter = speakers;
                speakerAdapter = new SpeakerAdapter(speakers, getContext());
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
