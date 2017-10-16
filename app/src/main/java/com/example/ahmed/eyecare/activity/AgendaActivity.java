package com.example.ahmed.eyecare.activity;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmed.eyecare.R;
import com.example.ahmed.eyecare.api.utils.RetrofitRequest;
import com.example.ahmed.eyecare.api.utils.RetrofitResponse;
import com.example.ahmed.eyecare.fragment.AgendaFragment;
import com.example.ahmed.eyecare.fragment.MyAgendaFragment;
import com.example.ahmed.eyecare.model.Agenda;
import com.example.ahmed.eyecare.model.Session;
import com.example.ahmed.eyecare.utils.DummyData;
import com.example.ahmed.eyecare.utils.Utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class AgendaActivity extends AppCompatActivity {

    private TextView tvAgena;
    private TextView tvMyAgena;
    AgendaFragment agendaFragment;
    MyAgendaFragment myAgendaFragment;
    List<Agenda> myAgenda;
    List<Session> mySession;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);
        findViewById();
        onClick();
        agendaFragment=AgendaFragment.newInstance();
        myAgendaFragment=MyAgendaFragment.newInstance();
        myAgenda = new ArrayList<>();
        loadData();
    }

    private void onClick() {
        tvAgena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvAgena.setTextColor(ContextCompat.getColor(AgendaActivity.this, R.color.header));
                tvAgena.setBackground(ContextCompat.getDrawable(AgendaActivity.this,R.drawable.borderwhite));
                Utils.goToFragment(AgendaActivity.this,agendaFragment,null,null);

                tvMyAgena.setTextColor(ContextCompat.getColor(AgendaActivity.this, R.color.white));
                tvMyAgena.setBackgroundResource(android.R.color.transparent);

            }
        });
        tvMyAgena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvMyAgena.setTextColor(ContextCompat.getColor(AgendaActivity.this, R.color.header));
                tvMyAgena.setBackground(ContextCompat.getDrawable(AgendaActivity.this,R.drawable.borderwhite));

                tvAgena.setTextColor(ContextCompat.getColor(AgendaActivity.this, R.color.white));
                tvAgena.setBackgroundResource(android.R.color.transparent);
                Utils.goToFragment(AgendaActivity.this,myAgendaFragment,null,null);

            }
        });
    }

    private void findViewById() {
        tvAgena = (TextView) findViewById(R.id.tv_agena);
        tvMyAgena = (TextView) findViewById(R.id.tv_my_agena);
    }
    private void loadData(){
        RetrofitRequest.getAllAgenda(DummyData.userID, new RetrofitResponse<List<com.example.ahmed.eyecare.model.Agenda>>() {
            @Override
            public void onSuccess(List<Agenda> agendas) {
               for(Agenda currentAgenda:agendas){
                   mySession = new ArrayList<>();
                   for(Session session:currentAgenda.getSessions()){
                       if(session.getAddToAgenda()){
                           mySession.add(session);
                       }
                   }
                   if(mySession.size()>0) {
                       Agenda agenda = new Agenda();
                       agenda.setDayNumber(currentAgenda.getDayNumber());
                       agenda.setEventDate(currentAgenda.getEventDate());
                       agenda.setSessions(mySession);
                       myAgenda.add(agenda);
                   }
               }
                agendaFragment.setData(agendas);
                myAgendaFragment.setData(myAgenda);
                Utils.goToFragment(AgendaActivity.this,agendaFragment,null,null);
            }

            @Override
            public void onFailed(String errorMessage) {

                Toast.makeText(AgendaActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
