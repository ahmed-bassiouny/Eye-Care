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
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmed.eyecare.R;
import com.example.ahmed.eyecare.adapter.AttendeeAdapter;
import com.example.ahmed.eyecare.api.utils.RetrofitRequest;
import com.example.ahmed.eyecare.api.utils.RetrofitResponse;
import com.example.ahmed.eyecare.model.AttendeLisWithLetter;
import com.example.ahmed.eyecare.model.Attendee;
import com.example.ahmed.eyecare.utils.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AttendeeListFragment extends Fragment {

    private RecyclerView recycleview;
    private Toolbar mToolbar;
    private MenuItem mSearchAction;
    private boolean isSearchOpened = false;
    private EditText edtSeach;
    private ProgressBar progress;
    private AttendeeAdapter attendeeAdapter;
    private List<Attendee> attendeeList;
    LinearLayoutManager linearLayoutManager;
    private int pageNumber = 1;
    private String lastCharaterToSearch="";


    public AttendeeListFragment() {
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
        return inflater.inflate(R.layout.fragment_attendee_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViewById(view);
        pageNumber = 1;
        loadData();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    private void loadData() {
        progress.setVisibility(View.VISIBLE);
        RetrofitRequest.getAllAttendee(pageNumber,lastCharaterToSearch, new RetrofitResponse<List<AttendeLisWithLetter>>() {
            @Override
            public void onSuccess(final List<AttendeLisWithLetter> attendeLisWithLetters) {
                if (attendeLisWithLetters == null)
                    onFailed(getString(R.string.cant_load_data));
                else {
                    // loop to get attendees
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            attendeeList = new ArrayList<>();
                            for (AttendeLisWithLetter item : attendeLisWithLetters)
                                for (Attendee attendee : item.getAttendees())
                                    attendeeList.add(attendee);
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if(pageNumber==Constant.PAGE_NUMBER) {
                                        attendeeAdapter = new AttendeeAdapter(attendeeList, getContext());
                                        recycleview.setAdapter(attendeeAdapter);
                                    }else {
                                        attendeeAdapter.updateAttendee(attendeeList);
                                    }
                                    progress.setVisibility(View.GONE);
                                    recycleview.setVisibility(View.VISIBLE);
                                }
                            });
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
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recycleview.setLayoutManager(linearLayoutManager);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Attendee");

        mToolbar.setNavigationIcon(R.drawable.ic_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        recycleview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int itemno = linearLayoutManager.findLastVisibleItemPosition();
                if (Constant.COUNT_ITEMS_PER_REQUEST-1 == itemno) {
                    if ((attendeeList.size() / Constant.COUNT_ITEMS_PER_REQUEST) == pageNumber) {
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
                    pageNumber=1;
                    if (s.toString().trim().isEmpty()) {
                        lastCharaterToSearch="";
                    }else {
                        lastCharaterToSearch=s.toString();
                    }
                    loadData();
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
}
