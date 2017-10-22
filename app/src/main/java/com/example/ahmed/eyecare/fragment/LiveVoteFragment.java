package com.example.ahmed.eyecare.fragment;


import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmed.eyecare.R;
import com.example.ahmed.eyecare.model.Answer;
import com.example.ahmed.eyecare.model.Question;
import com.example.ahmed.eyecare.utils.Constant;
import com.example.ahmed.eyecare.utils.DummyData;
import com.example.ahmed.eyecare.utils.SharedPref;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LiveVoteFragment extends Fragment {


    private Toolbar mToolbar;
    ChildEventListener childEventListener;
    DatabaseReference myRef;
    FrameLayout radioGroupFrame;
    RadioGroup radioGroup;
    Button btnSubmit;
    TextView tvQuestion,tvNoQuestion;
    List<Answer> listAnswer;
    ProgressBar progress;
    int userId;
    boolean questionActive=false;
    int indexAnswer = 0;
    String lastQuestionKey = "";

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
        userId=SharedPref.getMyAccount(getContext()).getUserId(); // get id from sharedpref
        initListenerFirebase();
        loadLastQuestionKeyOpenVote();
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
                setQuestios(dataSnapshot);
                isNewQuestion(questionActive);
                progress.setVisibility(View.GONE);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                setQuestios(dataSnapshot);
                isNewQuestion(questionActive);
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

    private void isNewQuestion(boolean question) {
        if(question){
            tvQuestion.setVisibility(View.VISIBLE);
            radioGroupFrame.setVisibility(View.VISIBLE);
            btnSubmit.setVisibility(View.VISIBLE);
            tvNoQuestion.setVisibility(View.GONE);
        }else {
            tvQuestion.setVisibility(View.GONE);
            radioGroupFrame.setVisibility(View.GONE);
            btnSubmit.setVisibility(View.GONE);
            tvNoQuestion.setVisibility(View.VISIBLE);
        }
    }

    private void findViewById(View view) {
        mToolbar = view.findViewById(R.id.toolbar);
        radioGroupFrame = view.findViewById(R.id.radio_group_frame);
        btnSubmit = view.findViewById(R.id.btn_submit);
        tvQuestion=view.findViewById(R.id.tv_question);
        tvNoQuestion=view.findViewById(R.id.tv_no_question);
        progress = view.findViewById(R.id.progress);
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
        btnSubmit.setVisibility(View.INVISIBLE);
    }
    private void onClick(){
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    btnSubmit.setEnabled(false);
                    Answer answer = listAnswer.get(indexAnswer);
                    myRef.child(lastQuestionKey).child("answers").child(indexAnswer + "").child("votes").setValue((answer.votes + 1), new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                            FirebaseDatabase.getInstance().getReference(Constant.USER).child(String.valueOf(userId)).setValue(lastQuestionKey);
                            questionActive=false;
                            isNewQuestion(questionActive);
                            btnSubmit.setEnabled(true);
                        }
                    });
                }catch (Exception e){
                    Toast.makeText(getContext(), getString(R.string.cant_send_vote), Toast.LENGTH_SHORT).show();
                    btnSubmit.setEnabled(true);
                }
            }
        });
    }
    private void setQuestios(DataSnapshot dataSnapshot){
        Question item = dataSnapshot.getValue(Question.class);
        if(item.getActivated() && !dataSnapshot.getKey().equals(lastQuestionKey)) {
            lastQuestionKey = dataSnapshot.getKey();
            listAnswer =item.answers;
            tvQuestion.setText(item.question);
            radioGroupFrame.removeAllViews();
            radioGroup = new RadioGroup(getContext());
            radioGroup.setOrientation(RadioGroup.VERTICAL);
            for(int i=0;i<item.answers.size();i++) {
                Answer answer = listAnswer.get(i);
                RadioButton radioButton = new RadioButton(getContext());
                radioButton.setText(answer.answer);
                final int finalI = i;
                radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(isChecked){
                            indexAnswer = finalI;
                        }
                    }
                });
                radioGroup.addView(radioButton);
            }
            radioGroupFrame.addView(radioGroup);
            questionActive=true;
        }
    }

    private void loadLastQuestionKeyOpenVote(){
        FirebaseDatabase.getInstance().getReference(Constant.USER).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    String id = snapshot.getKey();
                    if(id.equals(String.valueOf(userId))){
                        lastQuestionKey=snapshot.getValue(String.class);
                        break;
                    }
                }
                addListener();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
