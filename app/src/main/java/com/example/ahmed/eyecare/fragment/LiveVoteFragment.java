package com.example.ahmed.eyecare.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmed.eyecare.R;
import com.example.ahmed.eyecare.model.Answer;
import com.example.ahmed.eyecare.model.Question;
import com.example.ahmed.eyecare.utils.Constant;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LiveVoteFragment extends Fragment {


    private Toolbar mToolbar;
    RecyclerView recycleview;
    ChildEventListener childEventListener;
    DatabaseReference myRef;
    RadioGroup radioGroup;
    Button btnSubmit;
    TextView tvQuestion;
    List<Answer> listAnswer;
    String questionKey;


    public LiveVoteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_live_vote, container, false);
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
        initListenerFirebase();
        addListener();
        btnSubmit.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onStop() {
        super.onStop();
        removeListener();
    }

    private void removeListener() {
        myRef.removeEventListener(childEventListener);
    }

    private void addListener() {
        myRef = FirebaseDatabase.getInstance().getReference(Constant.QUESTION);
        if (childEventListener != null) ;
        myRef.addChildEventListener(childEventListener);

    }

    private void initListenerFirebase() {
        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Question item = dataSnapshot.getValue(Question.class);
                if(item.getActivated()) {
                    questionKey = dataSnapshot.getKey();
                    listAnswer=item.answers;
                    tvQuestion.setText(item.question);
                    for(Answer answer : item.answers) {
                        RadioButton radioButton = new RadioButton(getContext());
                        radioButton.setText(answer.answer);
                        radioGroup.addView(radioButton);
                    }
                    // found question
                    if (true) {
                        btnSubmit.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
    }

    private void findViewById(View view) {
        mToolbar = view.findViewById(R.id.toolbar);
        recycleview = view.findViewById(R.id.recycleview);
        radioGroup = view.findViewById(R.id.radio_group);
        btnSubmit = view.findViewById(R.id.btn_submit);
        tvQuestion=view.findViewById(R.id.tv_question);
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
        radioGroup.setOrientation(RadioGroup.VERTICAL);
    }
    private void onClick(){
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int indexAnswer = (radioGroup.getCheckedRadioButtonId()-1);
                Answer answer = listAnswer.get(indexAnswer);
                Toast.makeText(getContext(), answer.votes+"", Toast.LENGTH_SHORT).show();
                myRef.child(questionKey).child("answers").child(indexAnswer+"").child("votes").setValue((answer.votes+1));

            }
        });
    }
}
