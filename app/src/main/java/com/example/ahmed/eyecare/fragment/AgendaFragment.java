package com.example.ahmed.eyecare.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ahmed.eyecare.R;
import com.example.ahmed.eyecare.adapter.SessionAgendaAdapter;
import com.example.ahmed.eyecare.api.utils.RetrofitRequest;
import com.example.ahmed.eyecare.api.utils.RetrofitResponse;
import com.example.ahmed.eyecare.model.Agenda;
import com.example.ahmed.eyecare.utils.DummyData;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AgendaFragment extends Fragment {

    List<Agenda> agendaList;
    ArrayList<String> days;
    Spinner spinner;
    RecyclerView recyclerView;
    SessionAgendaAdapter sessionAgendaAdapter;
    static AgendaFragment agendaFragment;

    public AgendaFragment() {
        // Required empty public constructor
    }
    public static AgendaFragment newInstance(){
        if(agendaFragment==null)
            agendaFragment=new AgendaFragment();
        return agendaFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_agenda, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViewById(view);
        loadData();
        onClick();
    }

    private void findViewById(View view) {
        spinner=view.findViewById(R.id.spinner);
        recyclerView=view.findViewById(R.id.recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
    }

    private void loadData(){
        RetrofitRequest.getAllAgenda(DummyData.userID, new RetrofitResponse<List<Agenda>>() {
            @Override
            public void onSuccess(List<Agenda> agendas) {
                agendaList=agendas;
                getDays();
            }

            @Override
            public void onFailed(String errorMessage) {

                Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void getDays(){
        days = new ArrayList<>();
        for(Agenda item:agendaList){
            days.add(item.getDayNumber()+" "+item.getEventDate());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, days);
        spinner.setAdapter(adapter);
    }
    private void onClick(){

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                agendaList.get(position).getSessions();
                if(sessionAgendaAdapter==null)
                    sessionAgendaAdapter=new SessionAgendaAdapter(getContext());
                sessionAgendaAdapter.setData(agendaList.get(position).getSessions());
                recyclerView.setAdapter(sessionAgendaAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
