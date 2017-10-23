package com.example.ahmed.eyecare.activity;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmed.eyecare.R;
import com.example.ahmed.eyecare.adapter.AgendaDayAdapter;
import com.example.ahmed.eyecare.adapter.SessionAgendaAdapter;
import com.example.ahmed.eyecare.adapter.SessionMyAgendaAdapter;
import com.example.ahmed.eyecare.api.utils.RetrofitRequest;
import com.example.ahmed.eyecare.api.utils.RetrofitResponse;
import com.example.ahmed.eyecare.interfaces.OnClickListenerAdapter;
import com.example.ahmed.eyecare.model.Agenda;
import com.example.ahmed.eyecare.model.Session;
import com.example.ahmed.eyecare.utils.DummyData;
import com.example.ahmed.eyecare.utils.SharedPref;

import java.util.ArrayList;
import java.util.List;

public class AgendaActivity extends AppCompatActivity implements OnClickListenerAdapter {

    private Toolbar mToolbar;
    private TextView tvAgena;
    private TextView tvMyAgena;
    private ProgressBar progress;

    // adapter
    SessionAgendaAdapter sessionAgendaAdapter;
    SessionMyAgendaAdapter sessionMyAgendaAdapter;
    AgendaDayAdapter agendaDayAdapter;
    List<Agenda> agendaList;
    List<Agenda> myAgendaList;

    Spinner spinner;
    RecyclerView recyclerView;
    boolean currentTabMyAgenda = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);
        findViewById();
        onClick();
        agendaDayAdapter = new AgendaDayAdapter(this);
        loadAgenda();
    }

    private void onClick() {
        tvAgena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvAgena.setTextColor(ContextCompat.getColor(AgendaActivity.this, R.color.header));
                tvAgena.setBackground(ContextCompat.getDrawable(AgendaActivity.this, R.drawable.borderwhite));

                tvMyAgena.setTextColor(ContextCompat.getColor(AgendaActivity.this, R.color.white));
                tvMyAgena.setBackgroundResource(android.R.color.transparent);
                currentTabMyAgenda = false;
                loadAgenda();

            }
        });
        tvMyAgena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvMyAgena.setTextColor(ContextCompat.getColor(AgendaActivity.this, R.color.header));
                tvMyAgena.setBackground(ContextCompat.getDrawable(AgendaActivity.this, R.drawable.borderwhite));

                tvAgena.setTextColor(ContextCompat.getColor(AgendaActivity.this, R.color.white));
                tvAgena.setBackgroundResource(android.R.color.transparent);
                currentTabMyAgenda = true;
                loadMyAgenda();
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    if (currentTabMyAgenda) {
                        if (myAgendaList != null) {
                            if (sessionMyAgendaAdapter == null)
                                sessionMyAgendaAdapter = new SessionMyAgendaAdapter(AgendaActivity.this);
                            sessionMyAgendaAdapter.setData(myAgendaList.get(position).getSessions());
                            recyclerView.setAdapter(sessionMyAgendaAdapter);
                        }
                    } else {
                        if (agendaList != null) {
                            if (sessionAgendaAdapter == null)
                                sessionAgendaAdapter = new SessionAgendaAdapter(AgendaActivity.this);
                            sessionAgendaAdapter.setData(agendaList.get(position).getSessions());
                            recyclerView.setAdapter(sessionAgendaAdapter);
                        }
                    }
                } catch (Exception e) {
                    finish();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void findViewById() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        tvAgena = (TextView) findViewById(R.id.tv_agena);
        tvMyAgena = (TextView) findViewById(R.id.tv_my_agena);
        spinner = (Spinner) findViewById(R.id.spinner);
        progress = (ProgressBar) findViewById(R.id.progress);
        recyclerView = (RecyclerView) findViewById(R.id.recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mToolbar.setNavigationIcon(R.drawable.ic_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void loadAgenda() {
        showData(false);
        RetrofitRequest.getAllAgenda(SharedPref.getMyAccount(this).getUserId(), new RetrofitResponse<List<com.example.ahmed.eyecare.model.Agenda>>() {
            @Override
            public void onSuccess(List<Agenda> agendas) {
                agendaList = agendas;
                ArrayList<String> days = new ArrayList<>();
                for (Agenda item : agendas) {
                    days.add(item.getDayNumber() + "," + item.getEventDate());
                }
                agendaDayAdapter.setDays(days);
                spinner.setAdapter(agendaDayAdapter);
                if(days.size()==1){
                    spinner.setEnabled(false);
                }else{
                    spinner.setEnabled(true);
                }
                showData(true);
            }

            @Override
            public void onFailed(String errorMessage) {
                Toast.makeText(AgendaActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadMyAgenda() {
        showData(false);
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (agendaList == null || agendaList.size() == 0)
                    return;
                myAgendaList = new ArrayList<>();
                for (Agenda currentAgenda : agendaList) {
                    ArrayList<Session> mySession = new ArrayList<>();
                    for (Session session : currentAgenda.getSessions()) {
                        if (session.isMyAgenda()) {
                            mySession.add(session);
                        }
                    }
                    if (mySession.size() > 0) {
                        Agenda agenda = new Agenda();
                        agenda.setDayNumber(currentAgenda.getDayNumber());
                        agenda.setEventDate(currentAgenda.getEventDate());
                        agenda.setSessions(mySession);
                        myAgendaList.add(agenda);
                    }
                }
                final ArrayList<String> days = new ArrayList<>();
                for (Agenda item : myAgendaList) {
                    days.add(item.getDayNumber() + "," + item.getEventDate());
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        agendaDayAdapter.setDays(days);
                        spinner.setAdapter(agendaDayAdapter);
                        if(days.size()==1){
                            spinner.setEnabled(false);
                        }else{
                            spinner.setEnabled(true);
                        }
                        showData(true);
                    }
                });
            }
        }).start();

    }

    private void showData(boolean show) {
        if (show) {
            progress.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            spinner.setVisibility(View.VISIBLE);
        } else {
            progress.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            spinner.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(final int position) {
        if(!currentTabMyAgenda) {
            Session session = agendaList.get(spinner.getSelectedItemPosition()).getSessions().get(position);
            session.setisMyAgenda(true);
            agendaList.get(spinner.getSelectedItemPosition()).getSessions().set(position, session);
            sessionAgendaAdapter.updateData(agendaList.get(spinner.getSelectedItemPosition()).getSessions());
            RetrofitRequest.addToMyAgenda(SharedPref.getMyAccount(AgendaActivity.this).getUserId(), session.getId(), new RetrofitResponse<Boolean>() {
                @Override
                public void onSuccess(Boolean aBoolean) {
                    Toast.makeText(AgendaActivity.this, R.string.session_added, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailed(String errorMessage) {
                    Session session = agendaList.get(spinner.getSelectedItemPosition()).getSessions().get(position);
                    session.setisMyAgenda(false);
                    agendaList.get(spinner.getSelectedItemPosition()).getSessions().set(position, session);
                    sessionAgendaAdapter.updateData(agendaList.get(spinner.getSelectedItemPosition()).getSessions());
                    Toast.makeText(AgendaActivity.this, R.string.session_added_error, Toast.LENGTH_SHORT).show();

                }
            });

        }
    }
}
